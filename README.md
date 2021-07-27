# loja-estoque

## Descrição
Serviço responsável por manter o estoque da loja virtual

## Tecnologias utilizadas

- [Java](https://www.java.com/pt-BR/) - Linguagem de programação utilizada para desenvolvimento do módulo backend
- [Spring Boot](https://spring.io/projects/spring-boot/) - Framewwork utilizado para auxiliar no desenvolvimento do módulo backend
- [MongoDB](https://www.mongodb.com/pt-br) - Banco de dados
- [Docker](https://www.docker.com/) - Tecnologia utilizada para a entrega da aplicação
- [Lombok](https://projectlombok.org/) - Para simplificar o código 
- [Swagger](https://swagger.io/) - Para documentar e testar a api
- [JUnit](https://junit.org/junit5/) - Para testes unitários e de integração
- [Mockito](https://site.mockito.org/) - Para testes unitários


## Testes unitários

#### O que foi usado?

- Plugins instalados via pom.xml para execução dos testes unitários no build
- JUnit 5
- Mockito
- JaCoCo para os devs ficarem atentos a cobertura, sem precisarem iniciar a esteira

#### Como verificar a cobertura de testes ?

- Na IDE execute o comando "mvn clean install" ou "mvn build", o arquivo /target/site/jacoco/index.html será gerado e toda a cobertura pode ser verificada.
- Sem IDE, use o prompt do windows ou o git bash e navegue até a raiz do projeto, execute o comando "mvn clean install" ou "mvn build", o arquivo /target/site/jacoco/index.html será gerado e toda a cobertura pode ser verificada.

#### Dependências:

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-launcher</artifactId>
            <version>1.7.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.7.1</version>
            <scope>test</scope>
        </dependency>


#### Plugins:

            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.4</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

## Health

Rotas disponíveis para verificar a saúde e métricas da aplicação:

- http://localhost:8080/actuator/health
- http://localhost:8080/falemais/actuator/info
- http://localhost:8080/falemais/actuator/prometheus

Com isso é possível utilizar softwares como o grafana para monitorar esse serviço.

## Docker

- Use o arquivo dockerfile na raiz do projeto e gere uma imagem, exemplo de comandos:
        
        docker build . -- tag repositorio/nome-da-imagem
        docker push tag repositorio/nome-da-imagem
        docker pull repositorio/nome-da-imagem