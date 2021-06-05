# DOCUMENTO GENERAL DE REQUISITOS
![](https://github.com/pabloMoreno-uma/g5-restaurante/blob/master/imagenes/booky.png)
### HISTORIAL DE REVISIONES
  * Primera Version - 1.0 (Fecha. 24 de Abril de 2021)
  * Segunda Version - 2.0 (Fecha. 05 de Junio de 2021)
  * Primera Revisión Segunda Version - 2.1 (Fecha. 06 de Junio de 2021)

### INTRODUCCIÓN
- #### PROPÓSITO
    En este documento general de requisitos vamos a recoger todos los requisitos necesarios para la creación de nuestro proyecto software. Además, sirviendo este de orientación para el cliente y los implicados en el proyecto, así como para tener una visión general y definida de todas las condiciones.
- ### ALCANCE
    Esta aplicación va dirigida a propietarios de restaurantes, los cuales desean contar con un sistema de reservas adaptado a aplicaciones moviles. Con esta aplicacion, se le ofrece a los clientes la posibilidad de realizar su reserva en el restaurante, contando tambien con toda la información relevante de su servicio.
- ### VISIÓN GENERAL
    Nuestra aplicación es una aplicación Android la cual ofrece a los usuarios realizar reservas en su restaurante favorito, además de consultar la carta en este y valorar los platos.

### DESCRIPCIÓN GENERAL DEL PRODUCTO
- #### FUNCIONES DEL PRODUCTO
    Como hemos mencionado anteriormente, nuestro proyecto se va a enfocar en realizar  una  aplicación  para móviles Android, capaz  de  realzar  distintas  labores en  la organización  y,  por  tanto,mejora de  los  servicios  de  un  restaurante  para  el propio usuario.Esta  aplicación  contará  con  la  capacidad  de  hacer  reservas,  de  registrarse  y  de  dejar reseñas de los menús pedidos por los usuarios, pudiendo ver el menú del propio restaurante desde la propia “app”.

- #### SUPOSICIONES Y DEPENDENCIAS
    Esperamos poder realizar la aplicación sin ningún inconveniente conforme a las sugerencias  y restriccionesque  nos da nuestro cliente. Pero  aquellos  factores  que pueden re-organizar y cambiar nuestros requisitos, en nuestro caso, va a ser nuestras capacidades de programación en Java para plataformas móviles de Android, ya que no hemos realizado  ninguna aplicación  en  dicha  plataforma,  así que  seguramente  nos encontramos inconvenientes en la creación de la base de datos o en la interfaz gráfica, pudiendo estos,cambiar muchos de nuestros requisitos, eliminarlos o generar nuevos.En cuanto a las dependencias de la “app”, podremos ver que hasta que no realicemos la capacidad del registro de los usuarios, no podremos implementar las valoraciones, ya que estas tienen que ser dejadas por usuarios registrados.Y estas a su vez,hasta que no implementemos el menú de los platos no podremos implementar las reseñas, así como la  visualización de los alérgenos del  plato.  Este  tipo  de  dependencias  nos  van  a proporcionar  la  capacidad  de tener  una  mejor  gestión  en  nuestro  proyecto,  ya  que deberemos realizarlo de forma procedural las distintas partes, sin ir por zonas separadas para  luego  unirlo  todo;  por  ello  creemos  que  esto  disminuirán nuestros  posibles problemas.

- #### RESTRICCIONES
    Las principales restricciones de nuestro proyecto de software son: 
    -> Nuestro desconocimiento de programar en plataformas Android, así como el uso de los respectivos programas requeridos.
    -> Así mismo, otra restricción fundamental es el no poder quedar de forma presencial por culpa de la pandemia vírica que nos acontece.

- #### REQUISITOS FUTUROS
    Podriamos implementar en un futuro una funcionalidad de cupones, basada en la fidelidad del usuario.

### REQUISITOS ESPECIFICOS
- #### REQUISITOS FUNCIONALES
    * RF.1) Los usuarios están clasificados en 2 niveles
        * **Usuario Registrado**. Puede  ver la carta con los platos (valoraciones y comentarios incluidos). Puede configurar distintos aspectos: su nombre de usuario, su contraseña y su número de teléfono. Todo usuario tendrá un número de identificación único.
        * **Administrador**. Tiene las mismas funciones que el usuario registrado, además de añadir y eliminar platos, cancelar reservas y eliminar a usuarios.
    * RF.2) El usuario puede realizar una reserva en el restaurante de una mesa en una fecha específica: dia, mes y turno, ya sea de mañana o de tarde, para un número de comensales de 1 a 8. No podrá realizar una reserva si ya existe una reserva realizada en la misma mesa a la misma fecha.
    * RF.3) El usuario dispone de su propio perfil, en el que podrá observar su nombre de usuario, sus datos y sus reservas.
      * El usuario podrá eliminar sus reservas desde dicho perfil, pulsando la que desee eliminar.
    * RF.4) El usuario puede ver la carta.
      * Podra insertar calificaciones; las cuales constan de nota, esta puede tomar valores entre 0 y 10, y comentario, en cualquier plato del menu. Las calificaciones no podrán ser borradas.
      * Los platos de la carta llevan indicados su descripción, precio, alérgenos y calificaciones.
    * RF.5) El administrador dispone de la función de insertar nuevos platos en la carta, a su vez tambien dispone de la funcionalidad de eliminarlos de la lista.
      * No podrá añadir un plato con el mismo nombre que otro que ya se encuentre dentro de la carta.
    * RF.6) El administrador dispone de la función de eliminar las reservas de cualquier usuario
    * RF.7) El administrador dispone de la función de eliminar usuarios de la app.
      * El administrador no se puede eliminar a si mismo.


- #### REQUISITOS NO FUNCIONALES
    * RNF.1) La aplicación estará programada en JAVA.
    * RNF.2) La arquitectura de la aplicación estará basada en cliente-servidor.
    * RNF.3) La aplicación debe estar optimizada.
      * No debe pesar demasiado.
      * No debe consumir mucha CPU.
    * RNF.4) La aplicación debe almacenar de forma segura los credenciales de sesión de los usuarios.
      * El email de registro no puede estar ya almacenado en la base de datos.
      * Las contraseñas serán almacenadas en la base de datos tras ser cifradas con el algoritmo de encriptación SHA1.
    * RNF.5) La aplicación estará disponible para dispositivos móviles Android en versiones 4.4 KitKat o superiores.
