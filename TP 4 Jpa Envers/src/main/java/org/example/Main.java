package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory y EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Iniciar transacción
        entityManager.getTransaction().begin();

        // Crear entidades
        Domicilio domicilio1 = Domicilio.builder()
                .nombreCalle("Republica del libano")
                .numero(550)
                .build();

        Cliente cliente1 = Cliente.builder()
                .dni(45720510L)
                .apellido("Almendros")
                .nombre("Matias")
                .build();

        Factura factura1 = Factura.builder()
                .fecha("15/08/2003")
                .numero(1)
                .total(1200000)
                .build();

        DetalleFactura detalle1 = DetalleFactura.builder()
                .cantidad(1)
                .subtotal(500000)
                .build();

        DetalleFactura detalle2 = DetalleFactura.builder()
                .cantidad(1)
                .subtotal(300000)
                .build();

        DetalleFactura detalle3 = DetalleFactura.builder()
                .cantidad(2)
                .subtotal(200000)
                .build();

        Articulo art1 = Articulo.builder()
                .cantidad(15)
                .denominacion("Heladera con pantalla táctil")
                .precio(500000)
                .build();

        Articulo art2 = Articulo.builder()
                .cantidad(20)
                .denominacion("Smart TV 4K 55 pulgadas")
                .precio(300000)
                .build();

        Articulo art3 = Articulo.builder()
                .cantidad(10)
                .denominacion("Bicicleta de montaña")
                .precio(100000)
                .build();

        Categoria hogar = Categoria.builder()
                .denominacion("Hogar")
                .build();

        Categoria electronica = Categoria.builder()
                .denominacion("Electronica")
                .build();

        Categoria deporte = Categoria.builder()
                .denominacion("Deporte")
                .build();

        //Setters

        //Domicilio1
        domicilio1.setCliente(cliente1);
        //Cliente1
        cliente1.setDomicilio(domicilio1);
        cliente1.setFacturas(Set.of(factura1));
        //Factura1
        factura1.setCliente(cliente1);
        factura1.setDetalles(Set.of(detalle1,detalle2,detalle3));
        //Detalle1
        detalle1.setFactura(factura1);
        detalle1.setArticulo(art1);
        //Detalle2
        detalle2.setFactura(factura1);
        detalle2.setArticulo(art2);
        //Detalle3
        detalle3.setFactura(factura1);
        detalle3.setArticulo(art3);
        //Articulo1
        art1.setDetalle(Set.of(detalle1));
        art1.setCategorias(Set.of(hogar,electronica));
        //Articulo2
        art2.setDetalle(Set.of(detalle2));
        art2.setCategorias(Set.of(hogar,electronica));
        //Articulo3
        art3.setDetalle(Set.of(detalle3));
        art3.setCategorias(Set.of(deporte));

        //        Cliente cliente = entityManager.find(Cliente.class,1L);
        //        cliente.setNombre("Hardblister");
        //        cliente.setApellido("MainCypher");
        //        entityManager.merge(cliente);

        // Cerrar el EntityManager y EntityManagerFactory
        entityManager.persist(factura1);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}