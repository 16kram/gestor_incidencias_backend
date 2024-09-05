<b>Esta aplicación, desarrollada con Java, MySQL y Spring Boot, actúa como servidor en un sistema de gestión de incidencias, diseñado para hacer el seguimiento de los mantenimientos integrales en edificios de oficinas.

La aplicación utiliza la arquitectura REST para la comunicación entre servicios y emplea Tokens para la autenticación, garantizando la seguridad en el acceso a los datos.

Este servidor funciona en conjunto con el proyecto de página web 'gestor_incidencias_frontend', proporcionando la lógica de negocio y gestionando la interacción con la base de datos.</b>

Notas:</BR>
Certificado y clave privada SSL para servidor Apache en Windows creados con Win-Acme.<BR>
Certificado y clave privada SSL para servidor Apache en Raspbian creados con Certbot.<BR>
Certificado y clave privada SSL para el servidor Tomcat de SpringBoot--> Se utilizan los archivos .pem creados para el Servidor Apache y se utiliza openSSL para convertirlos a un archivo .p12(PKCS12):</BR>
openssl pkcs12 -export \
  -in estebanpa.ddns.net-crt.pem \
  -inkey estebanpa.ddns.net-key.pem \
  -out keystore.p12 \
  -name estebanpa \
  -CAfile estebanpa.ddns.net-chain.pem \
  -caname root

La contraseña para proteger el archivo PKCS12 que nos pide en el proceso de conversión, se utilizará en la configuración de SpringBoot.<BR>
Se añade el archivo keystore.p12 en el directorio src/main/resources, y se añade en application.properties:<BR>
server.port=8443<BR>
server.ssl.key-store=classpath:keystore.p12<BR>
server.ssl.key-store-password=tu-contraseña<BR>
server.ssl.key-store-type=PKCS12<BR>
server.ssl.key-alias=estebanpa
