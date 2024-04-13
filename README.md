[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/t218cK-M)
# 4.Hafta Ödevi
- **Kullanılan servisleri istediğiniz bir database ile bağlayın. `(30 PUAN)`** \
MySQL kullanıldı.

- Bir uygulama için service katmanına tüm gerekli senaryolar için unit test yazın. `(20 PUAN)(Daha fazla yapan bonus 10 PUAN)` \
User servisi için unit test yazıldı. Application servisi için yazıldı.

- Tüm uygulamaları tek bir repo haline getirin.bu repo içerisinde ilgili bütün servisler olmalı. Gw, discovery servis ve diğer servisler`(5 PUAN)`
Tüm servisler tek bir repo içerisinde bulunmaktadır.
- Dokümantasyon ekleyin. projeye dökümantasyon ekleyin. Swagger, read.me dosyası, mimari çizim, servislerin
  endpointleri`(20 PUAN)`
Readme.md dosyası eklendi. Swagger eklendi.
- Best practices, isimlendirme kurallarına uyum. Unit test isimlendirme`(10 PUAN)`
İsimlendirme kurallarına uyuldu. Unit test isimlendirme yapıldı.
- Uygulamanın doğru çalışması. `(15 PUAN)`
Demo videosu eklendi.
## BONUS
- Uygulamaların dockerize edilmesi ve docker compose dosyası yazılması. `(10 PUAN)`
Dockerize edildi ve docker-compose dosyası yazıldı.
- Design Pattern kullanımı. `(10 PUAN)`\
Builder pattern, Singleton pattern, Interface Segregation Principle, Dependency Injection, Interceptor kullanıldı.

![Adsız-2024-04-07-1353.png](.github%2Fassets%2FAds%C4%B1z-2024-04-07-1353.png)

- servisleri docker içine taşıdıktan sonra bir türlü davranış değişmiyorsa image'i silmek gerekli Örneğin gateway bir türlü bağlayamadıysanız ve intellij kullanıyorsanız services altındaki images temizlemek çözüm olabilir. veya endpointleri temizlediğinizde swagger'a yansımıyorsa aynı şekilde imajları temizlemek çözüm olabilir.
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

---
*Eğitmen - Cem DIRMAN*  
*Kolay Gelsin*
