### 1- MVC kavramını açıklar mısınız ? Neden ihtiyaç duyuluyor. Java’da nasıl kurgulanıyor. Object Oriented katmanları nelerdir ? 

#### MVC çok kere Katmanlı Mimari ile karıştırılmaktadır. Model, View ve Controller ifadelerinden oluşan isim bu patterni oluşturan triangledır. Http isteği ile gelen data'nın front-controller olan dispatcher servlet ile doğru controller'a kanalize edilmesi ve service'te datanın işlenmesi ile response'un tekrardan dispatcher servleta geri dönmesidir. Dispatcher Servlet geri dönen datayı view resolvera gönderir ve akış o noktada biter. Spring Boot uygulamalarında data Jackson ile parse edilir, istekler Tomcat Sunucu ile dinlenir ve Tomcatte bulunan Dispatcher-Servlet ile handle edilir. View Resolver olarak java tarafında Thymeleaf, JSP, Freemarker, Mustache gibi kütüphaneler kullanılmaktadır. MVC'nin tercih edilme sebebi SOLID prensiblerinde Single Responsibilty'i net bir şekilde sunması ve sorumlulukların ayrılması ile geliştirme ve test edilebilirliğin de artmasıdır.

#### MVC Katmanlı Mimari olarak açıklayacak olursak verinin valide edilmesi, işlenmesi, kalıtsallaştırılmasıdır ve bunun SOLID ile Clean Code prensiblerine uygun bir şekilde yapılması için geliştirilmiş bir pattern diyebiliriz. Uygulumamda da kullanmış olduğum bu modelin katmanları controller,service,repository,model şeklindedir. 

#### - Controller katmanı gelen datanın validasyondan geçip java objesine parse edilmesidir. 
#### - Service katmanı java objesi olarak gelen datanın işlenmesi ve DAO tarafına gönderilmesidir.
#### - Repository katmanı aynı zamanda Data Access Object katmanıdır. JPA ve Hibernate bu kısımda context oluşturması ve dbye fazladan requestleri engellemesi, ddl generate etmesi, transaction işlemlerini sağlaması, sql exceptionları handle etmesi ile bu katmanı sadece bir DAO olarak görmemizi engellemektedir.
#### - Model katmanı uygulamanın verilerini temsil eden sınıfları içerir. Veri modeli sınıfları, veri erişim katmanından alınan verileri nesneler halinde temsil eder ve bu nesnelerin üzerinde işlemler yapılır. Bu katman, verilerin işlenmesi ve yönetilmesi için gerekli iş mantığını içerir.
#### - DTO katmanı, middleware katmanı da diyebiliriz, database fieldların tamamını özellikle id gibi önemli fieldları expose etmemizi engeller. Frontend tarafına sadece istediği fieldları vererek kolaylık sağlar. Entitylerden ayrı olması ile entitylere özgürlük sağlar.

### 2- Birbirinden bağımsız iki platformun birbiriyle haberleşmesi nasıl sağlanabilir. Örneğin, X platformu Java ile yazılmış olsun, Y platform u C# ile. Bu iki platformun bir biri ile iletişim halinde request-response ilişkisi kurması gerekiyor. Bu yapıyı nasıl sağlarız.

#### İki platform arasında platform fark etmeksizin data'nın transfer edilmesi için bir çok yöntem vardır. Bunlardan bir tanesi Kafka, RabbitMQ, MSMQ gibi tek taraflı iletişimi asynq olarak yapan Message Queue tekniği. Datayı ByteCode'a çevirip http isteğinden daha hızlı davranan GRPC tekniği de bunlardan biridir. Ama en çok yaygın olan RESTfuldur. RESTful bir api diğer bir service'e http isteği ile data gönderir veya istek ile data alır. Http istiğinin headerında url, http methodunun yanında content-type ifadesini de girmemiz ve application/json olarak belirtmemiz gerekmektedir. Bu Restful iletişimin alemeti farikasıdır. Http hem request body hem response body json olarak girilmektedir. Service'lere gelen json formatındaki data belli başlı kütüphanelerle parse edilmektedir. Java'da en bilinen parserların başında Jackson gelmektedir. 

### 3- Bir web sayfasında ekran sürekli Backend’ den veya bir başka yapı tarafından güncelleniyor. Siz, web sayfasını refresh etmeden bu güncel bilgiyi anlık ekrana nasıl yansıtırsınız. 

#### Bir frontend sorusu olmakla beraber zamanında aldığım vue.js bootcampi ile hatırladıklarımdan betimleyebileceğim kadarı şöyledir; Web siteleri birer html dosyalarıdır ve bu dosyalar WEB 2 ya kadar birer static sayfaydılar. WEB 2'nun ve js'nin gelmesi ile sayfalardaki bilgilerin güncellenmesi bir ihtiyaç haline gelmiştir. Bir haber sitesinde köşedeki döviz kurunun güncellenmesi için tüm sayfanın baştan yüklenmesi kronik bir rahatsız olarak görülmektedir. Bu sebeple sayfalar birer companente bağlanmakta ve hatta tüm sayfalar componentler ile tek bir htmle bağlanmaktadır. Virtual DOM ların gelmesi ile köşede duran döviz kuru için tüm html sayfasının güncellenmesi değil sadece ilgili componentin güncellenmesi sağlanmıştır. Bir Virtual DOM Tabanlı js kütüphanesi olan Vue.js ile ve onun componenti ile yansıtırdım. 

### 4- Bir for döngüsü ile aşağıdaki çıktıyı yazar mısınız. 
```

*  
**  
****  
******  
********  
********** 

```
response:
```

public class app {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int n = 5;            
        for (int i = 0; i < n; i++) {
        if (i == 0) System.out.println("*");
        sb.append("**");
        System.out.println(sb);
        }
    }
}

```
### 5- Firmada çalışman için sana remote bir linux server verildi. Elinde ip adresi port bilgisi kullanıcı adi ve şifren var. Server a erişimi nasıl test edersin, Server a nasıl erişirsin, Server a nasıl dosya atarsın, Serverdan nasıl dosya çekersin.

#### Verilen bilgilerle uzaktaki bir Linux sunucuya erişmek ve işlemler yapmak için Secure Shell (SSH) protokolünü kullanabilirz.

```
TEST
ssh -T git@github.com

GİRİŞ
ssh mark@66.220.144.0 -p 8080

UPLOAD
scp -P 8080 dosya_adresi mark@66.220.144.0:dosya_hedef_adresi

DOWNLOAD
scp -P 8080 mark@ip_adresi:dosya_adresi dosya_hedef_adresi

```
### 6- Local database kurulumu (mysql, postgresql veya herhangi bir database) - Java spring uygulaması ayağa kaldırılması, - İki adet tablo yer alamalı ve bu tabloların birbirleriyle bağı olmalıdır. (Örn: şirket ve çalışan gibi), - Java spring uygulamasında ekleme,silme,güncelleme,listeleme gibi servisler yer almalıdır ve responseda yapılan işlem detayı return edilmelidir. - Ekleme,silme,güncelleme,listeleme işlemlerini postman vb ile işlem yapılabilmelidir.
### [**Company-Management-System**](https://github.com/Eukolos/enoca-assignment)

### 7-Apache Solr servisine yazılacak bir query örneği Apache Solr kullanılan sql programlarından daha farklı runtime bir database. Solr a hali hazırda kayıtlı bir alan olduğunu düşünelim. Alanın ismi “updatedAt” long tipinde tutulan bir alan. Ben 2020 Ocak ayından sonraki verileri getir dediğimde solr a nasıl bir query yazılmalı. http://example?query= kısmını nasıl doldurmalıyım?
```
curl http://example/?query=updatedAt:[2020-01-01T00:00:00Z%20TO%20*]
```