#### Graphic Store
Projeto que simula uma loja online de uma gráfica que oferece produtos aos clientes.


Arquitetura : Microsservices


O projeto implementa as tecnologias:
- Java 17
- Springboot
- SpringCloud
- SpringData
- Jpa
- PostgreSQL
- Docker
- Kafka
- Openfeign

Progress:
 
v0.1
 - Product Application
   - receive request to register a product
   - repository, service, mapper classes tested
   - exceptions handler controller implemented
   - flyway database migration implemented
   - avro schema registry implemented
   - todo: implement kafka communication
   - todo: implement openfeign communication