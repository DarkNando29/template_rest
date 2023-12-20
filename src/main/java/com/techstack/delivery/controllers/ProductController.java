package com.techstack.delivery.controllers;

import com.techstack.delivery.entities.Product;
import com.techstack.delivery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning products:");
        return "products";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    // Afficher le formulaire de modification du Product
    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    /**
     * Save product to database.
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product/" + product.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
