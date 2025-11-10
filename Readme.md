Tamam! README.md dosyasını, **projenin Melasoft’a Backend Developer test case’i için geliştirildiğini** belirtecek şekilde güncelledim:

```markdown
# Invoice Processor API

Spring Boot tabanlı XML fatura işleme API'si. Bu proje, **Melasoft’a Backend Developer test case’i** kapsamında geliştirilmiştir. Base64 ile kodlanmış XML faturaları işleyerek veritabanına kaydeder.

## Özellikler

- Base64 encoded XML kabulü
- XSD şemasına göre XML validasyonu
- JAXB ile XML'den Java objesine dönüşüm
- H2 in-memory database
- Spring Data JPA
- Global exception handling
- Custom HTTP status kodları (480 - XML Validation Error)

## Teknolojiler

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- H2 Database
- JAXB
- Maven

## Proje Yapısı

```

invoice-processor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/invoice_processor/
│   │   │       ├── controller/
│   │   │       │   └── InvoiceController.java
│   │   │       ├── service/
│   │   │       │   ├── InvoiceService.java
│   │   │       │   └── impl/
│   │   │       │       ├── InvoiceServiceImpl.java
│   │   │       │       └── XmlProcessingService.java
│   │   │       ├── repository/
│   │   │       │   └── InvoiceRepository.java
│   │   │       ├── entity/
│   │   │       │   └── InvoiceEntity.java
│   │   │       ├── dto/
│   │   │       │   ├── InvoiceRequest.java
│   │   │       │   └── ApiResponse.java
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── XmlValidationException.java
│   │   │       ├── generated/ (JAXB tarafından otomatik oluşturulur)
│   │   │       └── InvoiceProcessorApplication.java
│   │   └── resources/
│   │       ├── xsd/
│   │       │   └── faktura.xsd
│   │       └── application.properties
├── pom.xml
└── README.md

````

## Kurulum

1. Projeyi clone edin:
   ```bash
   git clone <repository-url>
   cd invoice-processor
````

2. Maven bağımlılıklarını yükleyin:

   ```bash
   mvn clean install
   ```

3. Uygulamayı başlatın:

   ```bash
   mvn spring-boot:run
   ```

   Veya IDE'nizden `InvoiceProcessorApplication.java` dosyasını çalıştırın.

## API Kullanımı

### Fatura İşleme

**Endpoint:** `POST /api/invoices`

**Request:**

```json
{
  "base64xml": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPEZha3R1cmEgeG1sbnM6eHNkPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSIgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIgeG1sbnM9Imh0dHA6Ly9jcmQuZ292LnBsL3d6b3IvMjAyMy8wNi8yOS8xMjY0OC8iPgogICAgPE5hZ2xvd2VrPgogICAgICAgIDxLb2RGb3JtdWxhcnphIGtvZFN5c3RlbW93eT0iRkEgKDIpIiB3ZXJzamFTY2hlbXk9IjEtMEUiPkZBPC9Lb2RGb3JtdWxhcnphPgogICAgICAgIDxXYXJpYW50Rm9ybXVsYXJ6YT4yPC9XYXJpYW50Rm9ybXVsYXJ6YT4KICAgICAgICA8RGF0YVd5dHdvcnplbmlhRmE+MjAyMy0wOC0yOVQxMjozNDoxMy43ODAyNTcxWjwvRGF0YVd5dHdvcnplbmlhRmE+CiAgICAgICAgPFN5c3RlbUluZm8+U2FtcGxvZmFrdHVyPC9TeXN0ZW1JbmZvPgogICAgPC9OYWdsb3dlaz4KICAgIDxQb2RtaW90MT4KICAgICAgICA8RGFuZUlkZW50eWZpa2FjeWpuZT4KICAgICAgICAgICAgPElQPjk3ODEzOTkyNTk8L05JUD4KICAgICAgICAgICAgPE5hem3hPkFCQyBBR0Qgc3AuIHogby4gby48L05hem3hPgogICAgICAgIDwvRGFuZUlkZW50eWZpa2FjeWpuZT4KICAgICAgICA8QWRyZXM+CiAgICAgICAgICAgIDxLb2RLcmFqdT5QTDwvS29kS3JhanU+CiAgICAgICAgICAgIDxBZHJlc0wxPnVsLiBLd2lhdG93YSAxPC9BZHJlc0wxPgogICAgICAgIDwvQWRyZXM+CiAgICAgICAgPERhbmVLb250YWt0b3dlPgogICAgICAgICAgICA8RW1haWw+ZXhhbXBsZUBleGFtcGxlLmNvbTwvRW1haWw+CiAgICAgICAgPC9EYW5lS29udGFrdG93ZT4KICAgIDwvUG9kbWlvdDE+..."
}
```

**Başarılı Response:**

* **Status:** `201 Created`
* **Body:**

```json
{
  "message": "Invoice saved successfully"
}
```

**Hata Durumları:**

* **Status:** `480 Bad Request` - XML validasyon hatası
* **Status:** `400 Bad Request` - Geçersiz istek formatı
* **Status:** `500 Internal Server Error` - Sunucu hatası

## XSD ve JAXB Kullanımı

Proje, XML şeması tanımı için `src/main/resources/xsd/faktura.xsd` dosyasını kullanır. JAXB (Java Architecture for XML Binding) ile XSD'den Java sınıfları otomatik olarak oluşturulur.

### JAXB Sınıflarını Yeniden Oluşturma

JAXB sınıflarını yeniden oluşturmak için:

```bash
mvn clean compile
```

Bu komut, `target/generated-sources/jaxb` dizininde Java sınıflarını oluşturacaktır.

## Veritabanı

Geliştirme ortamında H2 in-memory database kullanılmaktadır.

### H2 Console

Uygulama çalışırken H2 konsoluna erişmek için:

1. Browser'da `http://localhost:8080/h2-console` adresine gidin
2. Bağlantı bilgileri:

   * **JDBC URL:** `jdbc:h2:mem:invoicedb`
   * **User Name:** `sa`
   * **Password:** (boş bırakın)

### Tablo Yapısı

```sql
CREATE TABLE invoices (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nip VARCHAR(20) NOT NULL,
  p1 DATE,
  p2 VARCHAR(100),
  created_at DATE
);
```

## Geliştirme Notları

* XML validasyonu başarısız olursa, API 480 status kodu döndürür
* Base64 decode işlemi UTF-8 encoding kullanır
* JAXB unmarshalling işlemi öncesinde XML mutlaka validate edilir
* Tüm exception'lar GlobalExceptionHandler tarafından yakalanır

## Lisans

Bu proje, **Melasoft’a Backend Developer test case’i** kapsamında eğitim ve değerlendirme amaçlı olarak geliştirilmiştir.

