
# Kredinbizde Loan Application - [Loom Demo(5min)](https://www.loom.com/share/c443cd3be6b144089cc2333bdbf383e6?sid=149856d1-58a1-4931-8836-c7214fa4b4fb)

## Technologies
- Java 21, Spring Boot, Microservices, Docker, MySQL, Kafka, Redis, Eureka

## Prerequisites

- Docker/Docker Compose installed on your machine.
- Git installed on your machine.

## Clone the Repository

```bash
git clone https://github.com/Definex-Java-Spring-Bootcampp/week-4-rsemihkoca
cd week-4-rsemihkoca
```

## Setup


### Just Run the Docker Compose


Execute the following command to bring down existing Docker containers, volumes, and remove orphaned containers, then bring the Docker environment back up:

```bash
docker-compose down -v --remove-orphans && docker-compose up
```

## Architecture


![Adsız-2024-04-07-1353.png](.github%2Fassets%2FAds%C4%B1z-2024-04-07-1353.png)

- En dışarıda bulunan gateway servisi, tüm istekleri karşılar ve ilgili servislere yönlendirir.
- Discovery servisi, tüm servislerin kayıtlı olduğu servistir. FeignClient ile servisler arası iletişimi sağlar.
- User servisi, kullanıcılarla ilgili işlemleri yapar.
- Application servisi, başvurularla ilgili işlemleri yapar.
- Bank servisi, bankaların sunduğu kredilerin güncel listesini tutar.
- Kafka asenkron mesajlaşmayı sağlar.
- Redis cacheleme yapar. Database yükünü azaltır.
- MySQL servislerin veritabanıdır.
- Akbank, Garanti gibi entegrasyonlar dış kaynaklardır. Direkt olarak sistemin içinde bulunmazlar.

### Application Service



Execute the following command to bring down existing Docker containers, volumes, and remove orphaned containers, then bring the Docker environment back up:

```bash
docker-compose down -v --remove-orphans && docker-compose up
```

## Architecture


Faydalanılan kaynaklar:
- Servisleri docker içine taşıdıktan sonra bir türlü davranış değişmiyorsa image'i silmek gerekli Örneğin gateway bir türlü bağlayamadıysanız ve intellij kullanıyorsanız services altındaki images temizlemek çözüm olabilir. veya endpointleri temizlediğinizde swagger'a yansımıyorsa aynı şekilde imajları temizlemek çözüm olabilir.
- kafka'yı bu makaleye borçluyum: https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/

- Api gateway:
```shell
➜  week-4-rsemihkoca git:(main) ✗ curl -X 'GET' \
  'http://localhost:8081/api/v1/user/' \
  -H 'accept: */*'
[{"userId":"1","name":"Cem","age":30},{"userId":"2","name":"Cem","age":25},{"userId":"3","name":"Cem","age":28},{"userId":"4","name":"Cem","age":27},{"userId":"5","name":"Semih","age":24}]%                      
➜  week-4-rsemihkoca git:(main) ✗ curl -X 'GET' \
  'http://localhost:8888/api/v1/user/' \
  -H 'accept: */*'
[{"userId":"1","name":"Cem","age":30},{"userId":"2","name":"Cem","age":25},{"userId":"3","name":"Cem","age":28},{"userId":"4","name":"Cem","age":27},{"userId":"5","name":"Semih","age":24}]%  
```


# 4.Hafta Ödevi
- **Kullanılan servisleri istediğiniz bir database ile bağlayın. `(30 PUAN)`** \
MySQL kullanıldı.

- **Bir uygulama için service katmanına tüm gerekli senaryolar için unit test yazın. `(20 PUAN)(Daha fazla yapan bonus 10 PUAN)`** \
User servisi için unit test yazıldı. Application servisi için yazıldı.

- **Tüm uygulamaları tek bir repo haline getirin.bu repo içerisinde ilgili bütün servisler olmalı. Gw, discovery servis ve diğer servisler`(5 PUAN)`** \
Tüm servisler tek bir repo içerisinde bulunmaktadır.
- **Dokümantasyon ekleyin. projeye dökümantasyon ekleyin. Swagger, read.me dosyası, mimari çizim, servislerin
  endpointleri`(20 PUAN)`** \
Readme.md dosyası eklendi. Swagger eklendi. Mimari çizim eklendi. Servislerin endpointleri eklendi.
- **Best practices, isimlendirme kurallarına uyum. Unit test isimlendirme`(10 PUAN)`** \
İsimlendirme kurallarına uyuldu. Unit test isimlendirme yapıldı.
- **Uygulamanın doğru çalışması. `(15 PUAN)`** \
Demo videosu eklendi.
## BONUS
- **Uygulamaların dockerize edilmesi ve docker compose dosyası yazılması. `(10 PUAN)`** \
Dockerize edildi ve docker-compose dosyası yazıldı.
- Design Pattern kullanımı. `(10 PUAN)`\
  Interceptor, Builder pattern, Singleton pattern, Interface Segregation Principle, Dependency Injection kullanıldı.
