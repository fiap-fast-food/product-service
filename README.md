# Product-Service
Serviço responsável por operacionalizar os produtos(lanches)


### Arquitetura
<img src="assets/arquiteturafase04.jpg">

### Evidência sonar
<img src="assets/cobertura_sonar.png">

## Comandos para rodar os testes

<b>Testes de integração
```
mvn test -P integration-test
``` 
Testes de sistema
```
mvn test
```
<b>BDD test
```
mvn test -P system-test
```

## Swagger

```
http://localhost:8081/swagger-ui/index.html
```

# Entregável fase 04
### Para essa fase 04 do projeto, estamos implementando os seguintes desafios:
* Quebra do Monolíico em Microserviços conforme a arquitetura acima
* Implementação de qualidade de código com sonar

