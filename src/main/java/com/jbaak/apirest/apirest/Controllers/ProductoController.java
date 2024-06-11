package com.jbaak.apirest.apirest.Controllers;

import com.jbaak.apirest.apirest.Entities.Producto;
import com.jbaak.apirest.apirest.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el produto"));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detalleProducto ) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el produto"));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el produto"));

        productoRepository.delete(producto);

        return "El producto se elimino correctamente";
    }

}
