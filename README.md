# Mikroservis Mimarisi Projesi

Bu proje, modern bir mikroservis mimarisi örneğidir. Spring Boot, Spring Cloud ve Docker teknolojilerini kullanarak geliştirilmiştir.

## Servisler

1. **Service Registry (Eureka Server)**
   - Port: 8761
   - Servis keşfi ve yönetimi
   - Tüm mikroservislerin kayıt merkezi

2. **Config Service**
   - Port: 8888
   - Merkezi konfigürasyon yönetimi
   - Git tabanlı konfigürasyon depolama

3. **User Service**
   - Port: 8081
   - Kullanıcı yönetimi
   - JWT tabanlı kimlik doğrulama
   - Rol tabanlı yetkilendirme

4. **Content Service**
   - Port: 8082
   - İçerik yönetimi
   - İçerik CRUD işlemleri
   - Kafka ile event yayınlama

5. **Comment Service**
   - Port: 8083
   - Yorum yönetimi
   - İçeriklere yorum ekleme
   - Kafka ile event yayınlama

6. **Analytics Service**
   - Port: 8084
   - İçerik ve kullanıcı analitikleri
   - Kafka ile event dinleme
   - İstatistik toplama

7. **API Gateway**
   - Port: 8060
   - Tüm servislerin tek giriş noktası
   - JWT token doğrulama
   - Load balancing

## Teknolojiler

- Java 21
- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Docker & Docker Compose
- JWT (JSON Web Token)
- Eureka Server
- Spring Cloud Gateway
- Spring Cloud Config
- Maven
- Lombok

## Gereksinimler

- JDK 21
- Docker & Docker Compose
- Maven
- Git

## Kurulum

1. Projeyi klonlayın:
```bash
git clone https://github.com/fthkarakus/java_microservice_example.git
cd java_microservice_example
```

2. Docker Compose ile servisleri başlatın:
```bash
docker-compose up -d
```

3. Servislerin başlamasını bekleyin (yaklaşık 1-2 dakika)

4. Servislerin durumunu kontrol edin:
```bash
docker-compose ps
```

## Servis URL'leri

- Service Registry: http://localhost:8761
- Config Service: http://localhost:8888
- User Service: http://localhost:8081
- Content Service: http://localhost:8082
- Comment Service: http://localhost:8083
- Analytics Service: http://localhost:8084
- API Gateway: http://localhost:8060
- Kafdrop: http://localhost:9000

## API Dokümantasyonu

Postman koleksiyonu `Microservices.postman_collection.json` dosyasında bulunmaktadır. Koleksiyonu kullanmak için:

1. Postman'i açın
2. Koleksiyonu import edin
3. Environment oluşturun ve `base_url` değişkenini `http://localhost:8060` olarak ayarlayın
4. Önce register endpoint'ini kullanarak bir kullanıcı oluşturun
5. Login endpoint'ini kullanarak token alın
6. Token'ı environment variable'a kaydedin
7. Diğer endpoint'leri kullanmaya başlayabilirsiniz

## API Endpoints

### Auth Service
- POST `/api/auth/register`: Yeni kullanıcı kaydı
- POST `/api/auth/login`: Kullanıcı girişi

### User Service
- GET `/api/users/{id}`: Kullanıcı bilgilerini getirme
- PUT `/api/users/{id}`: Kullanıcı bilgilerini güncelleme
- PUT `/api/users/{id}/change-password`: Şifre değiştirme

### Content Service
- POST `/api/contents`: İçerik oluşturma
- GET `/api/contents/{id}`: İçerik detayı
- PUT `/api/contents/{id}`: İçerik güncelleme

### Comment Service
- POST `/api/comments`: Yorum oluşturma
- GET `/api/comments/content/{id}`: İçeriğe ait yorumları getirme
- PUT `/api/comments/{id}`: Yorum güncelleme

### Analytics Service
- GET `/api/analytics/content/{id}`: İçerik analitiklerini getirme
- GET `/api/analytics/user/{id}`: Kullanıcı analitiklerini getirme

## Veritabanı Şeması

Her servis kendi PostgreSQL veritabanını kullanmaktadır:

- userdb: User Service
- contentdb: Content Service
- commentdb: Comment Service
- analyticsdb: Analytics Service

## Event-Driven Mimari

Servisler arası iletişim Apache Kafka üzerinden event-driven mimari ile sağlanmaktadır:

- Content Service: İçerik oluşturma/güncelleme/silme eventleri
- Comment Service: Yorum oluşturma/güncelleme/silme eventleri
- Analytics Service: Event'leri dinleyerek analitik verileri güncelleme

## Güvenlik

- JWT tabanlı kimlik doğrulama
- Role tabanlı yetkilendirme
- API Gateway üzerinden merkezi güvenlik kontrolü
- Şifrelerin BCrypt ile hashlenmesi

## Monitoring

- Kafdrop ile Kafka monitoring
- Eureka Dashboard ile servis durumu
- Actuator endpoints ile servis metrikleri

## Geliştirme

1. Yeni bir branch oluşturun:
```bash
git checkout -b feature/your-feature
```

2. Değişikliklerinizi commit edin:
```bash
git commit -m "Add your feature"
```

3. Branch'inizi push edin:
```bash
git push origin feature/your-feature
```

4. Pull request oluşturun

## Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için `LICENSE` dosyasına bakın. 