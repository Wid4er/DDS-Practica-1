<<<<<<< HEAD
Arranque: Con Java 17+ y Spring Tools Version: 5.4.0.RELEASE con Practica1Appliacation.java

Cuentas: admin/admin gestiona productos y exporta SQL; user/user y anónimo consulta el catálogo y utiliza su carrito.

Persistencia: catálogo y carritos de usuarios se guardan en data/practica1.mv.db. El carrito anónimo es temporal y los precios se actualizan al consultar el carrito.

Consola H2: http://localhost:8080/h2-console/ — JDBC: jdbc:h2:file:./data/practica1, usuario sa, contraseña password. En producción el acceso quedaría cerrado.

Se realizó un buscador de productos vía findByNameContainingIgnoreCase.

Los ficheros html y parte del código fué heredado de la carpeta fundamental.

@AutoWired aplicado en los objetos, sin embargo, Spring Tools advierte que no es necesario.
=======
# Práctica 1

- Arranque: Con Java 17+ y Spring Tools Version: 5.4.0.RELEASE con Practica1Appliacation.java

- Cuentas: `admin/admin` gestiona productos y exporta SQL; `user/user` y anónimo consulta el catálogo y utiliza su carrito.

- Persistencia: catálogo y carritos de usuarios se guardan en `data/practica1.mv.db`. El carrito anónimo es temporal y los precios se actualizan al consultar el carrito.

- Consola H2: http://localhost:8080/h2-console/ — JDBC: `jdbc:h2:file:./data/practica1`, usuario `sa`, contraseña `password`. En producción el acceso quedaría cerrado.

- Se realizó un buscador de productos vía findByNameContainingIgnoreCase.

- Los ficheros html y parte del código fué heredado de la carpeta fundamental.

- @AutoWired aplicado en los objetos, sin embargo, Spring Tools advierte que no es necesario.

>>>>>>> 708087c062e151003eb60bdae4dbdef35d650dad
