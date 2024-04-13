
# Kredinbizde Loan Application 

## Demonstration

* [Mimari Özet 5 dk](https://www.loom.com/share/c443cd3be6b144089cc2333bdbf383e6?sid=149856d1-58a1-4931-8836-c7214fa4b4fb)
* [Kullanım-1 5 dk](https://www.loom.com/share/c443cd3be6b144089cc2333bdbf383e6?sid=149856d1-58a1-4931-8836-c7214fa4b4fb)
* [Kullanım-2 5 dk](https://www.loom.com/share/04f3262a4cdb46cf87cb0b884db0ad17?sid=6f92a892-b0d6-4d9a-9d35-eb0bf0680743)


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

## Api Gateway[:8888]

- Gateway servisi, tüm istekleri karşılar ve ilgili servislere yönlendirir.

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

## Application Service[:8080]

- Application servisi, başvurularla ilgili işlemleri yapar.

### Create Application
<table>
<thead>
    <tr>
      <th width="200px">Method</th>
      <th width="800px">Path </th>
    </tr>
</thead>
<tbody>
  <tr width="600px">
    <td align="center">POST    </td>
    <td  align="center">/api/v1/application</td>
  </tr>
</table>

#### Description:
Creates a loan application. Endpoint checks if the user exists, loan id exists, and if the user has an active loan. If all conditions are met, the application is created and notification is sent to the user email.

### Get All Applications
<table>
<thead>
    <tr>
      <th width="200px">Method</th>
      <th width="800px">Path </th>
    </tr>
</thead>
<tbody>
  <tr width="600px">
    <td align="center">GET    </td>
    <td  align="center">/api/v1/application/</td>
  </tr>
</table>

#### Description:
Retrieve all active loan applications. Deactivated applications are not included in the response.

### Get Application By User Email
<table>
<thead>
    <tr>
      <th width="200px">Method</th>
      <th width="800px">Path </th>
    </tr>
</thead>
<tbody>
  <tr width="600px">
    <td align="center">GET    </td>
    <td  align="center">/api/v1/application/{email}</td>
  </tr>
</table>

#### Description:
Retrieve all active loan applications by user email. Deactivated applications are not included in the response.

### Health Check
<table>
<thead>
    <tr>
      <th width="200px">Method</th>
      <th width="800px">Path </th>
    </tr>
</thead>
<tbody>
  <tr width="600px">
    <td align="center">GET    </td>
    <td  align="center">/actuator/health</td>
  </tr>
</table>

#### Description:
Health check endpoint for the application service.


## Lessons Learned
- Servisleri docker içine taşıdıktan sonra bir türlü davranış değişmiyorsa image'i silmek gerekli Örneğin gateway bir türlü bağlayamadıysanız ve intellij kullanıyorsanız services altındaki images temizlemek çözüm olabilir. veya endpointleri temizlediğinizde swagger'a yansımıyorsa aynı şekilde imajları temizlemek çözüm olabilir. Bu davranışın sebebi dockerfile'ların cachelenmesi ancak servis sayım fazla olduğu için böyle yapmak zorundaydım diğer türlü multi-stage dockerfile'ları single stage yapıp belleği yormak zorunda kalacaktım.
- Hibernate ile veritabanı işlemleri yaparken değişikliklerin database'e yansımama problemlerinden biri hibernate'in cacheleme yapmasıdır. Eğer database ile hibernate arasında bir uyumsuzluk varsa hibernate'in cache'ini temizlemek gerekebilir.
- Kafka advertised listeners konusu çok önemli. Kafka'yı docker içinde çalıştırırken advertised listeners'ı doğru ayarlamak gerekiyor. Aksi takdirde client'lar broker'a bağlanamazlar. Bu konuda çok fazla zaman harcadım. Aşağıdaki makaleyi buldum ve bu makale sayesinde sorunu çözebildim. Bu konuda çok dikkatli olmak gerekiyor.
kafka'yı bu makaleye borçluyum: https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/
- .Net'te olduğu gibi Spring boot'ta da aspect yapısı çok değerli günü kurtarıyor gerçekten.
- Docker içinde databaseleri ayırırken ait oldukları servisle aynı network'e eklemek gerekiyor. Aksi takdirde bağlantı sağlanamaz. Hepsi aynı networkte olursa mysql portları çakışır. Bu yüzden ayrı networklerde olmaları gerekiyor.
- Mükemmelci anlayış deadline'ı kaçırmana sebep olabilir. Özellikle son haftalarda çok fazla detayla uğraşmak yerine önce işleyişi tamamlamak daha önemli olabilir. Çok fazla detayla uğraştım ve sonuç olarak deadline'ı kaçırdım. Bu yüzden mükemmelci olmak yerine verilen eforu tartarak ilerlemek daha önemli olabilir. Response time'ı header'a ekleme feature'ı buna örnektir.
- Swot analizi doğru sonuç verdi. Rabbitmq çıkarıp sadece kafka eklemek doğru bir karar oldu. İki başlı çözümden kullanılan tek başlı çözüme geçmek doğru bir karar oldu. Bu sayede daha az servis kullanıldı ve daha az karmaşıklık oluştu.  
- Mikroservisler ile çalışırken karmaşıklık artacağı için feature'ları küçük tutmak ve servisleri küçük tutmak önemli. Diğer türlü servisler arası dengesizlik oluşuyor.



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
