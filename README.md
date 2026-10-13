# Latihan OOP: Aplikasi Konsol Perpustakaan (Pertemuan 1 sampai 7)

## Aturan

- Jangan ubah `src/test/**`, `.github/**`, dan `.wakatime-project`.
- Ikuti diagram persis: nama kelas, field, constructor, dan method.
- Buat semua field `private`, kecuali `title` dan `year` di `LibraryItem` (`protected`).
- Pakai array biasa. Jangan pakai `List` atau `ArrayList`.
- Jangan pakai `System.out.println` di `Library` dan kelas model. Tampilan hanya di `Main` dan `LibraryApp`.
- Nyalakan WakaTime setiap kali mengerjakan (lihat Langkah 0).
- Kerjakan sendiri. Diskusi konsep boleh. Kode dan laporan harus hasil kerjamu.

## Langkah 0: Pasang WakaTime (wajib, sebelum menulis kode apa pun)

WakaTime mencatat waktu kamu mengetik kode. Dosen memakainya untuk menilai usaha.

1. Buat akun gratis di [wakatime.com](https://wakatime.com).
2. Salin API key dari [wakatime.com/settings/api-key](https://wakatime.com/settings/api-key). Jangan bagikan key ini.
3. Pasang plugin di editormu:
   - **NetBeans**: `Tools > Plugins`, cari "WakaTime". Jika tidak ada, ikuti [wakatime.com/netbeans](https://wakatime.com/netbeans).
   - **VS Code**: pasang ekstensi `WakaTime` (`WakaTime.vscode-wakatime`).
   - **IntelliJ IDEA**: `Settings > Plugins`, cari "WakaTime".
4. Tempel API key saat plugin memintanya.
5. Ketik kode beberapa menit, lalu buka [wakatime.com/dashboard](https://wakatime.com/dashboard). Pastikan project **oop-library** muncul.

Ketentuan:

- `.wakatime-project` berisi `oop-library`. Jangan ubah atau hapus.
- Waktu pengerjaan tanpa plugin tidak dihitung.
- Sertakan screenshot dashboard di laporan (lihat [Laporan (PDF)](#laporan-pdf)).

## Daftar Isi

1. [Aturan](#aturan)
2. [Langkah 0: Pasang WakaTime](#langkah-0-pasang-wakatime-wajib-sebelum-menulis-kode-apa-pun)
3. [Studi Kasus](#studi-kasus)
4. [Memulai](#memulai)
5. [Membuka Proyek](#membuka-proyek)
6. [Cara Membaca Diagram dan Bantuan](#cara-membaca-diagram-dan-bantuan)
7. [Bagian 1 sampai 7](#bagian-1-pertemuan-1-pengantar)
8. [Coba Aplikasinya](#coba-aplikasinya)
9. [Laporan (PDF)](#laporan-pdf)
10. [Pengumpulan](#pengumpulan)
11. [Penilaian](#penilaian)

## Studi Kasus

Perpustakaan Polinema meminjamkan tiga jenis koleksi:

- **Buku**: dipinjam 14 hari, punya ISBN dan penulis.
- **DVD**: dipinjam 3 hari, punya durasi.
- **Majalah**: dipinjam 7 hari, punya nomor edisi.

Setiap **anggota** punya kartu perpustakaan dan boleh meminjam paling banyak **3** koleksi. Petugas memakai aplikasi konsol untuk menambah koleksi, mendaftarkan anggota, meminjamkan, menerima pengembalian, dan mencari koleksi.

## Memulai

Pastikan WakaTime aktif ([Langkah 0](#langkah-0-pasang-wakatime-wajib-sebelum-menulis-kode-apa-pun)).

1. Di GitHub, klik **Use this template**, lalu **Create a new repository**.
2. Pilih **Private**, lalu ikuti arahan Dosen tentang siapa yang kamu undang.
3. Clone repositori barumu.
4. Commit dan push setiap selesai satu bagian.
5. Buka tab **Actions**. Autograder berjalan di setiap push dan menampilkan nilai kodemu.

Jalankan semua tes di komputermu:

```
mvn -q test
```

Jalankan satu kelas tes:

```
mvn -q test -Dtest=P02BookTest
```

Baca pesan tes yang gagal. Pesannya menjelaskan apa yang kurang.

## Membuka Proyek

**NetBeans**: `File > Open Project`, pilih folder repositori. Jalankan satu file dengan `Run File` atau `Shift+F6`.

**VS Code**: buka folder, pasang "Extension Pack for Java", lalu klik `Run` di atas `main`.

**Command line** (butuh JDK 17 atau lebih dan Maven):

```
mvn -q compile exec:java
```

Perintah ini menjalankan `Main`, satu-satunya titik awal program.

## Cara Membaca Diagram dan Bantuan

Diagram kelas adalah spesifikasi utama. Semua kelas, field, constructor, dan method yang kamu butuhkan ada di diagram.

| Simbol | Arti |
|---|---|
| `+` | `public` |
| `-` | `private` |
| `#` | `protected` |
| `nama : Tipe` | field atau parameter bernama `nama` bertipe `Tipe` |
| `metode() : Tipe` | method yang mengembalikan `Tipe` |
| `<<buat kelas ini>>` | kelas belum ada di starter, kamu yang membuatnya |
| garis dengan panah terbuka `-->` | asosiasi |
| garis dengan belah ketupat kosong `o--` | agregasi |
| garis dengan belah ketupat penuh `*--` | komposisi |
| garis putus-putus `..>` | dependensi |
| panah segitiga kosong `<\|--` | inheritance (`extends`) |
| `0..5`, `1`, `*` | multiplicity (jumlah objek) |

Baca per kotak kelas: field di atas, constructor dan method di bawah. Lalu baca garis antar kelas.

Buka Bantuan 1 dulu. Buka Bantuan 2 hanya jika masih buntu. Bantuan di README ini berbentuk pertanyaan, bukan jawaban. Jawabannya kamu cari sendiri dari diagram, dari dokumentasi Java, dan dari pesan tes yang gagal.

---

## Bagian 1 (Pertemuan 1): Pengantar

### Yang Diuji Oleh Autograder

- Baris **pertama** output `Main` adalah persis `Welcome to Polinema Library`.
- Setelah baris itu, kamu bebas menambah baris lain.

### Pikirkan Dulu

- Apa yang terjadi saat kamu menekan Run: apa yang dikerjakan compiler, dan apa yang dikerjakan JVM?
- Method mana yang pertama kali dijalankan Java, dan mengapa harus `static`?

<details>
<summary>Bantuan 1</summary>

- Cek `java -version` di terminal. Versi berapa yang kamu punya, dan apakah itu cukup?
- Java punya satu perintah baku untuk menampilkan teks ke layar. Kamu sudah pernah melihatnya di contoh kode lain atau di materi kuliah, coba ingat lagi bentuknya.
- Teks yang tampil di layar harus persis sama dengan teks di soal. Bagian mana dari perintah itu yang menentukan teks apa yang tampil?
- Perintah ini perlu ditulis di tempat yang dijalankan JVM duluan. Method mana itu, dan di mana letaknya di `Main.java`?

</details>

<details>
<summary>Bantuan 2 (langkah demi langkah, buka jika Bantuan 1 belum cukup)</summary>

1. Buka `Main.java`. Di mana komentar `TODO` berada? Apakah di dalam atau di luar kurung kurawal `main`?
2. Satu baris perintah cukup di sini. Bagian mana dari perintah itu diapit tanda kutip ganda?
3. Bandingkan teks yang kamu tulis huruf demi huruf dengan `Welcome to Polinema Library`. Apakah besar-kecil hurufnya sama? Apakah ada spasi tambahan di awal atau akhir?
4. Setiap baris perintah di Java diakhiri satu tanda baca. Tanda apa itu?
5. Jalankan `Main` (di NetBeans: klik kanan file, `Run File`, atau `Shift+F6`). Apa yang muncul di jendela Output?
6. Jika ada garis merah atau error, baca pesannya baris per baris. Error itu menunjuk ke baris berapa, dan apa yang berbeda dari perintah yang kamu kenal di Bantuan 1?
7. Jalankan `mvn -q test -Dtest=P01MainOutputTest`. Jika gagal, pesan tesnya membandingkan apa dengan apa? Baris mana dari outputmu yang diperiksa?

</details>

### Uji Pemahaman

Tidak dinilai, tulis jawabannya di laporan: sebutkan 5 benda di perpustakaan yang menurutmu adalah *objek*. Untuk masing-masing, tulis satu *data* yang dimilikinya dan satu *aksi* yang bisa dilakukannya.

### Periksa Hasil

Tes yang harus lulus: `P01MainOutputTest`.

---

## Bagian 2 (Pertemuan 2): Class dan Object

![Diagram Bagian 2](docs/p02-author-book.png)

### Yang Diuji Oleh Autograder

- Constructor menyimpan semua parameternya, dan getter mengembalikan nilai yang sama.
- `Author.getInfo()` mengembalikan teks berbentuk `nama (negara)`. Contoh: `Andrea Hirata (Indonesia)`.
- Buku yang baru dibuat **tersedia**.
- `checkOut()` berhasil (`true`) satu kali. Selama buku masih dipinjam, memanggilnya lagi menghasilkan `false`.
- `returnItem()` membuat buku bisa dipinjam lagi.
- Dua objek `Book` tidak saling memengaruhi.
- Di bagian ini field boleh `public` (seperti di diagram). Di Bagian 3 kamu akan menutupnya.

### Pikirkan Dulu

- Data apa saja yang perlu diingat setiap objek `Book`? Mana yang berasal dari parameter, mana yang diatur sendiri oleh objek?
- Agar objek tahu sedang dipinjam atau tidak, apa yang harus diingatnya?
- Di constructor, nama parameter sama dengan nama field. Bagaimana Java membedakannya?

<details>
<summary>Bantuan 1</summary>

- Field dideklarasikan di dalam kelas tapi di luar semua method. Untuk tiap field di diagram, tipe apa yang tertulis di sana?
- Constructor dan parameternya sering punya nama yang sama dengan field. Kata kunci apa yang membedakan "field milik objek ini" dari "parameter yang baru masuk"?
- "Dipinjam atau tidak" hanya punya dua kemungkinan jawaban. Tipe data apa di Java yang hanya punya dua nilai? Saat `Book` baru dibuat, mana dari dua nilai itu yang masuk akal?
- `checkOut()` mengembalikan `boolean`, dan hasilnya berbeda tergantung kondisi. Berapa jalur keluar (`return`) yang kamu perlukan, dan kondisi apa yang memisahkannya?
- Coba buat dua objek `Book` di `Main` dan ubah salah satunya. Apakah objek yang satu lagi ikut berubah? Apa artinya itu tentang bagaimana Java menyimpan objek?

</details>

<details>
<summary>Bantuan 2 (langkah per kelas, buka jika Bantuan 1 belum cukup)</summary>

- **`Author`**: Diagram menyebut dua data yang diingat `Author`. Bagaimana constructor-nya seharusnya mengisi keduanya? Setiap getter hanya punya satu tugas, tugas apa itu? `getInfo()` menggabungkan dua data itu menjadi satu teks berbentuk `nama (negara)`. Operator apa di Java yang menggabungkan teks?
- **`Book`**: Diagram menyebut empat data yang diingat `Book`, termasuk satu yang statusnya bukan berasal dari parameter constructor. Data mana itu, dan nilai apa yang masuk akal untuk buku yang baru dibuat?
- **`checkOut`**: Buku yang sedang dipinjam tidak boleh dipinjam lagi. Bagaimana method ini tahu buku sedang dipinjam? Jika sedang dipinjam, apa yang dikembalikan dan apa yang TIDAK boleh berubah? Jika tidak, apa yang berubah dan apa yang dikembalikan?
- **`returnItem`**: Method ini tidak mengembalikan nilai apa pun (`void`). Satu data apa di objek yang harus berubah agar buku bisa dipinjam lagi?
- Cara memeriksa tanpa tes: di `Main`, buat dua `Book`. Panggil `checkOut()` pada yang pertama dua kali. Apakah hasil panggilan pertama dan kedua sama? Lalu cetak `isAvailable()` kedua buku. Apakah hanya yang pertama berubah?

</details>

### Uji Pemahaman

Tidak dinilai: perhatikan kode ini, lalu tebak outputnya.

```java
Book a = new Book("1", "Clean Code", 2008);
Book b = a;
b.checkOut();
System.out.println(a.isAvailable());
Book c = new Book("2", "Refactoring", 1999);
System.out.println(c.isAvailable());
```

Mengapa hasil baris pertama dan kedua berbeda? Gambar `a`, `b`, dan `c` di stack, dan objeknya di heap.

### Periksa Hasil

Tes yang harus lulus: `P02AuthorTest` dan `P02BookTest`.

---

## Bagian 3 (Pertemuan 3): Enkapsulasi

![Diagram Bagian 3](docs/p03-encapsulation.png)

### Yang Diuji Oleh Autograder

Semua kelas:

- Semua field `private`. Bandingkan diagram Bagian 3 dengan Bagian 2.

`Book`:

- Judul `null`, kosong, atau hanya spasi **ditolak**. Tahun 0 atau negatif **ditolak**.
- Penolakan berarti `IllegalArgumentException` dilempar dan nilai lama **tetap**.
- Constructor juga menolak data tidak valid. `isbn` tidak boleh kosong.
- `isbn` read-only (tanpa setter). Status tersedia tidak punya setter dan hanya berubah lewat `checkOut()` dan `returnItem()`.

`Member` (kelas ini kamu isi dari stub sesuai diagram):

- `memberId` dan `loanCount` read-only (tanpa setter). `memberId` dan nama tidak boleh kosong.
- `setName` menolak nama kosong dan menjaga nama lama.
- Jumlah pinjaman selalu antara 0 dan 3. `addLoan()` dan `returnLoan()` mengembalikan `true` jika berhasil dan `false` jika sudah di batas.
- `canBorrow()` menjawab apakah anggota masih boleh meminjam.

### Pikirkan Dulu

- Jika `Member` punya `public int loanCount`, baris kode apa yang merusak aturan "maksimal 3"? Bagaimana `private` mencegahnya?
- Constructor dan setter punya aturan validasi yang sama. Bagaimana menghindari menulis aturannya dua kali?
- Apakah `canBorrow()` perlu field sendiri? Apa yang terjadi jika datanya tidak sinkron dengan `loanCount`?
- Mengapa `memberId` tidak boleh punya setter?

<details>
<summary>Bantuan 1</summary>

- Ubah modifier semua field menjadi `private`, lalu lihat apa yang error di `Main` kamu. Baris mana yang error, dan mengapa `private` membuatnya error?
- Di setter, apa yang terjadi jika kamu mengisi field dulu baru memeriksa, dibandingkan memeriksa dulu baru mengisi? Mana yang membiarkan data lama tetap aman saat ditolak?
- Untuk menolak data, exception apa yang disebut di "Yang Diuji Oleh Autograder"? Kata kunci apa yang melemparkan sebuah exception?
- Bolehkah constructor memanggil method lain milik kelasnya sendiri, termasuk setter? Apa untungnya jika aturan validasi hanya ditulis di satu tempat?
- Untuk mengenali teks kosong atau hanya spasi, class `String` punya banyak method. Method mana yang namanya terdengar seperti menjawab pertanyaan itu? Cari di dokumentasi Java.
- `canBorrow()` tidak disebutkan punya field sendiri di diagram. Bisakah jawabannya dihitung langsung dari `loanCount` setiap kali dipanggil, tanpa menyimpan apa pun tambahan?

</details>

<details>
<summary>Bantuan 2 (langkah per kelas, buka jika Bantuan 1 belum cukup)</summary>

- **Langkah awal**: ubah semua field `Author` dan `Book` menjadi `private`. Jalankan `P02AuthorTest` dan `P02BookTest`. Mengapa keduanya tetap lulus walau field sudah tertutup?
- **`Book.setTitle`**: Ada dua kondisi yang membuat judul ditolak. Kondisi mana yang harus diperiksa lebih dulu, dan mengapa (apa yang terjadi kalau kamu memanggil method pada sesuatu yang `null`)? Jika kedua pemeriksaan lolos, baris apa yang terakhir dijalankan?
- **`Book.setYear`**: Tahun berapa saja yang ditolak menurut "Yang Diuji Oleh Autograder"? Tulis kondisinya sebagai perbandingan angka.
- **`Book` constructor**: `isbn` juga punya aturan "tidak boleh kosong", mengapa polanya mirip dengan judul? Untuk judul dan tahun, apakah constructor perlu memeriksa sendiri, atau ada method lain di kelas yang sudah tahu caranya?
- **`Member` field**: Dari diagram, data apa saja yang diingat `Member`, dan tipe apa masing-masing? Satu di antaranya nilai awalnya bukan dari parameter, berapa nilai awal yang masuk akal?
- **`Member` constructor**: Pola pemeriksaannya mirip dengan constructor `Book` yang mana? Untuk nama, apakah constructor perlu menulis ulang aturan validasinya, atau bisa memanggil method yang sudah ada?
- **`Member.setName`**: Bandingkan dengan `Book.setTitle`. Apa yang sama persis, dan apa yang beda (nama field dan pesannya)?
- **Tanpa setter**: Dua data di `Member` disebut "read-only" di "Yang Diuji Oleh Autograder". Apa artinya itu untuk method yang kamu tulis, getter saja atau getter dan setter?
- **`canBorrow`**: Batas pinjam disebutkan di "Yang Diuji Oleh Autograder". Operator perbandingan apa yang menjawab "masih di bawah batas"?
- **`addLoan`**: Method ini gagal dalam kondisi yang sama dengan jawaban `canBorrow()` yang mana? Jika berhasil, data apa yang berubah dan berapa banyak?
- **`returnLoan`**: Kapan method ini seharusnya gagal? Pikirkan batas bawahnya, bukan batas atas. Jika berhasil, data apa yang berubah?

</details>

### Uji Pemahaman

Tidak dinilai: tulis satu baris kode di `Main` yang akan merusak aturan "maksimal 3 pinjaman" jika field `loanCount` `public`. Mengapa baris itu tidak bisa dikompilasi setelah field menjadi `private`?

### Periksa Hasil

Tes yang harus lulus: `P03EncapsulationTest` dan `P03MemberTest`. Tes Bagian 2 tidak boleh rusak.

---

## Bagian 4 (Pertemuan 4): Relasi Class

![Diagram Bagian 4](docs/p04-relations.png)

### Yang Diuji Oleh Autograder

`Book` dan `Author` (asosiasi):

- Buku baru belum punya penulis. `getAuthor()` mengembalikan objek `Author` **yang sama** dengan yang diberikan ke `setAuthor`, dan dua buku boleh berbagi satu `Author`.

`Member` dan `LibraryCard` (komposisi):

- Setiap `Member` punya kartu dengan nomor berbentuk `CARD-` diikuti `memberId`. Contoh: `CARD-M001`.
- Kartu **tidak** diberikan dari luar. `Member` tidak punya constructor yang menerima `LibraryCard`. Dua anggota punya dua kartu berbeda.

`Shelf` (agregasi):

- Kapasitas rak **5** buku. `addBook` mengembalikan `false` jika rak penuh atau bukunya `null`.
- `findByIsbn` mengembalikan objek `Book` yang sama (bukan salinan), atau `null` jika tidak ada.
- `countAvailable` menghitung buku yang saat ini tersedia.

`Librarian` (dependensi):

- Tidak punya field sama sekali.
- `lend(member, book)` berhasil hanya jika anggota boleh meminjam **dan** buku tersedia. Jika berhasil, buku dipinjam dan pinjaman anggota bertambah. Jika gagal, tidak ada yang berubah.
- `receive(member, book)` berhasil hanya jika buku sedang dipinjam dan anggota punya pinjaman. Jika berhasil, buku tersedia lagi dan pinjaman anggota berkurang.

### Pikirkan Dulu

Jawab untuk **setiap** garis relasi di diagram:

- Siapa yang **membuat** objek di ujung garis: kelas ini sendiri, atau kode di luarnya?
- Jika pemilik objek dibuang, apakah objek di ujung garis ikut hilang?
- Apakah objek di ujung garis perlu disimpan sebagai **field**, atau cukup muncul sebagai **parameter** method?
- Apa arti `0..5` dan `0..1` pada garis tersebut?

<details>
<summary>Bantuan 1</summary>

- Relasi yang disimpan sebagai field berarti kelas **mengingat** objek itu. Relasi dependensi tidak mengingat apa pun. Bagaimana itu terlihat di tanda tangan method `Librarian`?
- Pada komposisi, kapan field `LibraryCard` sebaiknya diisi, dari parameter yang diterima dari luar atau dibuat sendiri oleh objeknya? Mengapa diagram bilang `Member` tidak punya constructor yang menerima `LibraryCard`?
- Array di Java punya ukuran tetap sejak dibuat. Jika kamu ingin tahu "berapa slot yang sudah terisi" tanpa menghitung ulang setiap kali, apa yang perlu kamu simpan selain array itu sendiri?
- `findByIsbn` hanya boleh menelusuri slot yang terisi, bukan seluruh array. Bagaimana kamu membatasi perulangannya? Dan untuk membandingkan dua `String`, operator `==` membandingkan apa, dan method apa yang membandingkan isinya?
- `Librarian` tidak menyimpan field apa pun. Coba lihat lagi method `Member` dan `Book` yang sudah kamu buat di bagian sebelumnya. Adakah yang sudah menjawab "boleh pinjam?" atau "berhasil dipinjam?"
- Di `lend`, urutan pemeriksaan penting. Apa akibatnya jika kamu mengubah status buku dulu, baru belakangan sadar anggotanya sudah mencapai batas pinjam?

</details>

<details>
<summary>Bantuan 2 (langkah per kelas, buka jika Bantuan 1 belum cukup)</summary>

- **`Book` dan `Author` (asosiasi)**: Field apa yang perlu ditambahkan di `Book` agar ia bisa "mengingat" satu `Author`? Constructor `Book` tidak menerima `Author`, jadi nilai field itu sebelum `setAuthor` dipanggil adalah apa? Agar dua buku bisa berbagi satu `Author` yang sama persis, apakah `setAuthor` boleh membuat objek `Author` baru, atau harus menyimpan apa yang diterima?
- **`LibraryCard`**: Data apa yang diingat kelas ini menurut diagram? Bagaimana constructor dan getter-nya seharusnya terlihat, dibandingkan kelas sederhana lain yang sudah kamu buat?
- **`Member` (komposisi)**: Kapan `LibraryCard` sebaiknya dibuat, sebelum atau sesudah validasi `Member` lolos? Nomor kartu berbentuk `CARD-` diikuti `memberId`. Operator apa yang menggabungkan teks itu? Agar `getCard` selalu mengembalikan kartu yang sama (bukan kartu baru setiap dipanggil), di mana objek `LibraryCard` itu harus disimpan?
- **`Shelf`**: Data apa saja yang perlu diingat `Shelf` agar bisa membatasi kapasitas 5 dan tahu mana slot yang terisi? `addBook` punya dua alasan untuk gagal. Apa saja, dan kondisi mana yang diperiksa lebih dulu? `countAvailable` perlu memeriksa setiap buku yang tersimpan. Method apa di `Book`/`LibraryItem` yang menjawab "tersedia atau tidak"?
- **`Librarian` (dependensi)**: Tanpa field dan tanpa constructor khusus, bagaimana method-nya menerima `Member` dan `Book` kalau bukan lewat field?
  - `lend`: Apa dua syarat yang harus **sama-sama** benar agar peminjaman berhasil? Jika salah satu gagal di tengah jalan, apa yang harus **tidak berubah**?
  - `receive`: Kebalikan dari `lend`. Syarat apa yang membuatnya gagal, dan data apa yang harus kembali ke keadaan semula jika berhasil?

</details>

### Uji Pemahaman

Tidak dinilai: jika objek `Member` dibuang, apa yang terjadi pada `LibraryCard`-nya? Jika objek `Shelf` dibuang, apa yang terjadi pada `Book` di dalamnya? Jelaskan perbedaan keduanya dengan kata-katamu sendiri.

### Periksa Hasil

Tes yang harus lulus: `P04AssociationTest`, `P04ShelfAggregationTest`, `P04MemberCardCompositionTest`, dan `P04LibrarianDependencyTest`.

---

## Bagian 5 (Pertemuan 6): Inheritance

![Diagram Bagian 5](docs/p06-inheritance.png)

### Yang Diuji Oleh Autograder

- `Book`, `Dvd`, dan `Magazine` adalah subclass langsung dari `LibraryItem`.
- `LibraryItem` memvalidasi `title` dan `year` seperti `Book` di Bagian 3. Field `title` dan `year` bersifat `protected`, dan tidak ada field `public`.
- `Book` tidak lagi menyimpan `title`, `year`, dan status tersedia sendiri, dan tidak menulis ulang method yang sama dengan `LibraryItem`.
- `Dvd` dan `Magazine` menyimpan data khususnya sesuai diagram (`private`). Getter judul dan tahun serta `checkOut`/`returnItem` dipakai dari `LibraryItem`.
- Validasi setter ikut berlaku di semua subclass.
- Semua tes Bagian 2 sampai 4 masih lulus.

### Pikirkan Dulu

- Bandingkan `Book` di diagram Bagian 4 dengan `Dvd` di diagram Bagian 5. Apa yang **sama** di keduanya? Apa yang **khusus**?
- Kalimat "`Dvd` adalah sebuah `LibraryItem`" masuk akal. Apakah "`Library` adalah sebuah `Book`" juga masuk akal?
- Mengapa `title` dan `year` ditandai `#` dan bukan `-`?
- Jika `LibraryItem` punya constructor yang meminta `title` dan `year`, siapa yang harus mengisinya saat sebuah `Dvd` dibuat?

<details>
<summary>Bantuan 1</summary>

- Kata kunci `extends` menyatakan hubungan IS-A. Dari ketiga subclass di diagram, apa yang membuat masing-masing bisa dibilang "adalah sebuah `LibraryItem`"?
- Constructor tidak diwariskan. Jika subclass ingin memakai logika constructor parent, kata kunci apa yang dipanggil, dan di baris keberapa dalam constructor subclass ia harus berada?
- `protected` bisa diakses subclass. Field mana di diagram yang ditandai begitu, dan mengapa hanya dua field itu, bukan semuanya?
- Kerjakan bertahap, bukan sekaligus: pindahkan satu bagian kode, jalankan tes, lihat hasilnya, baru lanjut. Apa untungnya dibanding memindahkan semuanya lalu baru menjalankan tes?
- Jika sebuah tes lama yang tadinya hijau berubah merah setelah kamu memindahkan kode, apa kemungkinan penyebabnya?

</details>

<details>
<summary>Bantuan 2 (urutan kerja refactoring, buka jika Bantuan 1 belum cukup)</summary>

Kerjakan urut. Jalankan seluruh tes setelah **setiap** langkah. Hasilnya harus tetap sama dengan sebelum langkah itu, kecuali `P06InheritanceTest` yang baru lulus di akhir.

1. **Buat `LibraryItem`, salin dulu, jangan hapus dulu dari `Book`.** Data dan method apa saja di `Book` yang sebenarnya berlaku umum untuk semua koleksi, bukan khusus buku? Pindahkan itu (tersalin, bukan terhapus) ke `LibraryItem`, lalu ubah `title` dan `year` menjadi `protected`. Constructor `LibraryItem` perlu mengisi judul, tahun, dan status tersedia. Bisakah ia memanfaatkan setter yang sudah ada, seperti yang dilakukan constructor `Book` di Bagian 3?
2. **Hubungkan `Book` ke `LibraryItem`.** Ganti deklarasi kelasnya. Baris pertama constructor `Book` sekarang harus memanggil apa, dengan argumen apa?
3. **Hapus duplikat dari `Book`.** Setelah `LibraryItem` punya salinannya, apa yang tersisa di `Book` yang benar-benar khusus untuknya? Jalankan tes Bagian 2 sampai 4. Apakah semuanya masih lulus?
4. **Buat `Dvd`.** Data apa yang khusus dimiliki `Dvd` menurut diagram? Baris pertama constructor-nya memanggil apa?
5. **Buat `Magazine`** dengan pola yang sama. Data apa yang khusus untuknya?
6. Jika muncul error yang menyebut constructor `LibraryItem` "cannot be applied to given types", constructor subclass mana yang disebut di pesan itu, dan argumen apa yang sudah/belum diberikannya ke `super(...)`?

</details>

### Uji Pemahaman

Tidak dinilai: apa yang diwarisi `Dvd` dari `LibraryItem`, dan apa yang tidak? Mengapa constructor `LibraryItem` tidak ikut diwariskan, tetapi tetap harus dipanggil saat `Dvd` dibuat?

### Periksa Hasil

Tes yang harus lulus: `P06InheritanceTest`. Tes Bagian 2 sampai 4 tidak boleh rusak.

---

## Bagian 6 (Pertemuan 7): Overriding dan Overloading

![Diagram Bagian 6](docs/p07-override-overload.png)

### Yang Diuji Oleh Autograder

Method baru di `LibraryItem` (baca daftarnya di diagram):

- `loanDays()` mengembalikan **7**.
- `describe()` mengembalikan deskripsi singkat, dan `toString()` mengembalikan hasil yang sama dengan `describe()`.
- `extendLoan()` menambah perpanjangan **7** hari. `extendLoan(int days)` menambah sebanyak `days`, dan menolak 0 atau negatif dengan `IllegalArgumentException`.
- `getTotalLoanDays()` adalah lama pinjam ditambah semua perpanjangan.

Nilai yang diharapkan:

| Objek | `loanDays()` | `describe()` |
|---|---|---|
| `LibraryItem("Generic", 2000)` | 7 | `Generic (2000)` |
| `Book` tanpa penulis | 14 | `Clean Code (2008) by Unknown` |
| `Book` dengan penulis Robert Martin | 14 | `Clean Code (2008) by Robert Martin` |
| `Dvd("Inception", 2010, 148)` | 3 | `Inception (2010) [148 min]` |
| `Magazine("Tempo", 2024, 7)` | 7 | `Tempo (2024)` |

Constructor baru (lihat diagram):

- `Book(isbn, title, year, author)` langsung mengisi penulis.
- `Magazine(title, year)` memberi nomor edisi **1**. Versi 3 parameter tetap berfungsi.

Struktur:

- `Book` dan `Dvd` meng-override `loanDays()`. `Magazine` **tidak** meng-override-nya.

### Pikirkan Dulu

- Bandingkan `describe()` milik `Book` dan milik `LibraryItem`. Bagian mana yang sudah dikerjakan parent? Perlukah kamu menulisnya ulang?
- `Magazine` tidak perlu `loanDays()` sendiri. Mengapa? Versi siapa yang berjalan?
- `extendLoan()` dan `extendLoan(int)` sama-sama bernama `extendLoan`. Apa yang membedakannya bagi compiler? Bisakah salah satu memakai yang lain?
- Dua constructor `Book` hampir sama. Bagaimana menghindari menyalin isinya?

<details>
<summary>Bantuan 1</summary>

- Anotasi `@Override` membuat compiler menolak jika nama atau parameter salah. Method mana saja di tabel nilai yang diharapkan yang berbeda per kelas? Itu petunjuk method mana yang perlu di-override.
- Kata kunci `super` bisa dipakai bukan hanya untuk constructor, tetapi juga untuk memanggil versi method milik parent. Pada `describe()` tiap subclass, bagian mana dari hasilnya sudah dikerjakan `LibraryItem`, dan bagian mana yang khusus?
- Constructor boleh memanggil constructor lain di kelas yang **sama**, dengan kata kunci yang berbeda dari `super(...)`. Dua constructor `Book` di diagram hampir sama. Constructor mana yang lebih sederhana untuk dipanggil dari yang satunya?
- `toString()` sudah ada di setiap objek Java (lewat `Object`). Tabel nilai bilang `toString()` harus sama dengan `describe()`. Apakah itu berarti menyusun ulang teksnya, atau cukup memanggil satu method yang sudah ada?
- Nama penulis di `describe()` punya dua kemungkinan. Kapan teks `Unknown` dipakai?
- Perpanjangan hari perlu diingat di suatu tempat. `getTotalLoanDays()` harus benar untuk `Book` (14) dan `Dvd` (3) sekaligus. Kalau kamu menulis angka tetap di method itu, bisakah itu benar untuk keduanya? Method apa yang sudah tahu angka yang berbeda-beda per kelas?

</details>

<details>
<summary>Bantuan 2 (langkah per kelas, buka jika Bantuan 1 belum cukup)</summary>

- **`LibraryItem`**:
  - `loanDays()`: tabel menyebut berapa hari standarnya untuk item generik.
  - `describe()`: bentuk hasilnya `Generic (2000)`. Data apa yang disusun, dan tanda baca apa di antaranya?
  - `toString()`: method apa di kelas ini yang sudah menghasilkan teks yang persis diminta?
  - Field baru untuk total perpanjangan, tipe apa, dan nilai awal berapa?
  - `extendLoan(int days)`: kondisi apa yang ditolak (lihat "Yang Diuji Oleh Autograder")? Jika lolos, apa yang berubah pada field perpanjangan?
  - `extendLoan()` (tanpa parameter): bisakah ia memanfaatkan `extendLoan(int)` dengan satu angka tetap, alih-alih menulis ulang pemeriksaannya?
  - `getTotalLoanDays()`: dua angka apa yang dijumlahkan? Salah satunya harus didapat lewat pemanggilan method, method mana, dan mengapa bukan angka tetap?
- **`Book`**:
  - `loanDays()`: berapa hari menurut tabel?
  - `describe()`: bagaimana cara mengambil hasil `describe()` versi `LibraryItem`, lalu apa yang ditambahkan di belakangnya? Bentuk hasil akhirnya `Clean Code (2008) by Robert Martin` atau `... by Unknown`, kapan masing-masing dipakai?
  - Constructor 4 parameter: constructor 3 parameter sudah mengerjakan validasi `isbn`, judul, dan tahun. Bagaimana memanfaatkannya alih-alih menulis ulang? Setelah itu, data apa lagi yang perlu diisi?
- **`Dvd`**: `loanDays()` mengembalikan berapa hari menurut tabel? Untuk `describe()`, bentuk hasilnya `Inception (2010) [148 min]`. Bagian mana yang berasal dari parent, bagian mana yang ditambahkan `Dvd`?
- **`Magazine`**: tidak ada override sama sekali. Mengapa versi `LibraryItem` untuk `loanDays()` dan `describe()` sudah cukup benar untuknya? Constructor 2 parameter memberi nomor edisi tetap. Bagaimana ia memanggil constructor 3 parameter dengan nilai itu?
- Cara memeriksa tanpa tes: di `Main`, taruh satu `Book`, satu `Dvd`, dan satu `Magazine` dalam satu array bertipe `LibraryItem[]`, cetak `loanDays()` dan `describe()` tiap elemen, lalu bandingkan dengan tabel nilai yang diharapkan.

</details>

### Uji Pemahaman

Tidak dinilai: di `Main`, buat array bertipe `LibraryItem[]` berisi satu `Book`, satu `Dvd`, dan satu `Magazine`, lalu cetak `loanDays()` dan `describe()` masing-masing dengan satu perulangan. Variabelnya bertipe `LibraryItem`, tetapi hasilnya `14`, `3`, dan `7`, bukan `7` untuk semuanya. Siapa yang menentukan versi method mana yang berjalan: tipe variabel atau objeknya?

### Periksa Hasil

Tes yang harus lulus: `P07OverrideTest` dan `P07OverloadTest`.

---

## Bagian 7 (Gabungan P1 sampai P7): Aplikasi Konsol

![Diagram Bagian 7](docs/p08-console-app.png)

### Pikirkan Dulu

- `Library` tidak boleh mencetak apa pun. Mengapa? Apa yang kamu ganti di proyek akhir saat tampilannya menjadi GUI, dan apa yang tidak perlu disentuh?
- Array `items` berisi `Book`, `Dvd`, dan `Magazine` sekaligus. Mengapa itu diizinkan? Konsep apa namanya?
- Mengapa `LibraryApp` menerima `Scanner` dan `PrintStream` lewat constructor, bukan langsung memakai `System.in` dan `System.out`?
- Pada alur "pinjam": objek mana yang dipanggil, dan method apa yang berjalan di masing-masingnya?

### Yang Diuji Oleh Autograder: kelas `Library`

Buat kelas `Library` sesuai diagram. Tidak boleh ada `System.out` di dalamnya.

| Method | Aturan |
|---|---|
| `addItem` | `false` jika `null` atau array penuh (20 item). |
| `addMember` | `false` jika `null`, penuh (10 anggota), atau `memberId` sudah terdaftar. |
| `getItems`, `getMembers` | Array baru yang **hanya berisi elemen terisi**. |
| `findItemByTitle` | Judul dicocokkan **tanpa peduli huruf besar-kecil**. `null` jika tidak ada. |
| `findMember` | Berdasarkan `memberId`. `null` jika tidak ada. |
| `searchByKeyword` | Semua koleksi yang judulnya **memuat** kata kunci (tanpa peduli huruf besar-kecil). Jika tidak ada, kembalikan array **kosong**, bukan `null`. |
| `lend(memberId, title)` | `true` hanya jika anggota ada, koleksi ada, anggota boleh meminjam, dan koleksi tersedia. Setelah itu koleksi dipinjam dan pinjaman anggota bertambah. |
| `receive(memberId, title)` | `true` hanya jika anggota dan koleksi ada, koleksi sedang dipinjam, dan anggota punya pinjaman. Setelah itu koleksi tersedia lagi dan pinjaman anggota berkurang. |

<details>
<summary>Bantuan 1: Library</summary>

- Dua array (`items` dan `members`) perlu dibatasi kapasitasnya (20 dan 10). Kelas lain di bagian sebelumnya sudah menghadapi masalah yang sama. Bagaimana kelas itu tahu "berapa slot yang sudah terisi"?
- Tipe elemen array koleksi adalah `LibraryItem`, bukan `Book`. Konsep apa yang membuat `Book`, `Dvd`, dan `Magazine` boleh masuk ke array bertipe itu bersama-sama?
- `getItems`/`getMembers` harus mengembalikan array baru yang "hanya berisi elemen terisi", artinya bukan array penuh 20/10 slot. Cari di `java.util` sebuah utilitas untuk array biasa yang bisa memotong panjangnya.
- Untuk judul dan kata kunci yang harus dicocokkan "tanpa peduli huruf besar-kecil", `String` punya lebih dari satu method yang relevan: satu untuk membandingkan, satu untuk mengubah semua huruf jadi kecil. Mana yang cocok untuk `findItemByTitle`, dan mana untuk `searchByKeyword` (yang mencari "memuat", bukan "sama persis")?
- `lend` dan `receive` tidak perlu menulis ulang aturan peminjaman. Method apa di `Member` dan `LibraryItem` yang sudah menjawab "boleh pinjam?", "berhasil dipinjam?", "sedang dipinjam?"
- `searchByKeyword` harus mengembalikan array sepanjang jumlah hasil, tapi jumlah itu belum diketahui sebelum pencarian selesai. Bagaimana caranya menampung hasil sementara, lalu memotongnya ke ukuran yang pas di akhir?

</details>

<details>
<summary>Bantuan 2: Library (langkah per method, buka jika Bantuan 1 belum cukup)</summary>

Field yang dibutuhkan: nama, array koleksi dan penghitungnya, array anggota dan penghitungnya. Apa nilai awal yang masuk akal untuk kedua penghitung?

- **`addItem`**: Ada dua alasan method ini gagal (lihat tabel aturan). Apa saja, dan bagaimana urutan tiga langkah "simpan di slot yang tepat, naikkan penghitung, kembalikan hasil"?
- **`addMember`**: Sama seperti `addItem`, tapi dengan satu syarat tambahan soal `memberId`. Method apa yang sudah kamu tulis di bawah ini (`findMember`) yang bisa menjawab "id ini sudah dipakai atau belum"?
- **`getItems`** dan **`getMembers`**: Array penuh berukuran 20/10 tidak boleh dikembalikan apa adanya kalau belum semua slot terisi. Dari utilitas `Arrays` yang kamu temukan di Bantuan 1, argumen kedua apa yang harus diberikan supaya hasilnya pas sepanjang data yang terisi?
- **`findItemByTitle`** dan **`findMember`**: Perulangannya harus berhenti di slot terakhir yang terisi, bukan di akhir array. Mengapa? Jika tidak ada yang cocok sampai perulangan selesai, apa yang dikembalikan?
- **`searchByKeyword`**: Berapa ukuran maksimal array sementara yang cukup aman untuk menampung semua kemungkinan hasil? Setelah menelusuri dan mencocokkan (keduanya sama-sama diubah ke huruf kecil dulu), bagaimana memotong array sementara itu agar hasilnya pas? Apa yang terjadi secara otomatis kalau tidak ada yang cocok sama sekali?
- **`lend`**: Urutkan pemeriksaannya: anggota dan koleksinya ditemukan dulu, lalu dua syarat apa yang harus sama-sama benar sebelum apa pun diubah? Jika salah satu gagal di tengah, apa yang harus tetap sama seperti semula?
- **`receive`**: Kebalikan dari `lend`. Syarat apa yang membuatnya gagal, dan dua perubahan apa yang terjadi kalau berhasil?

</details>

### Kelas `LibraryApp`

Buat file `LibraryApp.java`. Perulangan menu (`run`) dan pembacaan input dengan `Scanner` (`readLine`, `readInt`) **sudah lengkap**. Salin, lalu isi setiap method yang masih bertanda `// TODO`. Bantuan untuk tiap method ada di bawah kode ini.

```java
package id.ac.polinema.library;

import java.io.PrintStream;
import java.util.Scanner;

public class LibraryApp {

    private Library library;
    private Scanner in;
    private PrintStream out;

    public LibraryApp(Library library, Scanner in, PrintStream out) {
        this.library = library;
        this.in = in;
        this.out = out;
    }

    // Perulangan tanpa akhir: menu tampil lagi dan lagi sampai pengguna memilih 0.
    public void run() {
        while (true) {
            printMenu();
            if (!in.hasNextLine()) {      // input habis: berhenti, jangan crash
                return;
            }
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1": showItems(); break;
                case "2": addBook(); break;
                case "3": addDvd(); break;
                case "4": addMagazine(); break;
                case "5": registerMember(); break;
                case "6": lendItem(); break;
                case "7": returnItem(); break;
                case "8": searchItems(); break;
                case "9": showMembers(); break;
                case "0":
                    out.println("Goodbye");
                    return;               // keluar dari run, perulangan berhenti
                default:
                    out.println("Invalid choice");
            }
        }
    }

    // Mencetak prompt (tanpa pindah baris), lalu membaca satu baris. Spasi di ujung dibuang.
    private String readLine(String prompt) {
        out.print(prompt);
        if (!in.hasNextLine()) {
            return "";
        }
        return in.nextLine().trim();
    }

    // Membaca bilangan bulat positif. Mengembalikan -1 jika isinya bukan bilangan positif.
    private int readInt(String prompt) {
        String text = readLine(prompt);
        if (text.isEmpty() || text.length() > 9) {
            return -1;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return -1;
            }
        }
        int value = Integer.parseInt(text);
        return value > 0 ? value : -1;
    }

    private void printMenu() {
        // TODO: cetak judul, sepuluh baris menu, dan prompt "Choose: " sesuai tabel "Teks yang harus dicetak".
    }

    // Satu baris untuk satu koleksi. Dipakai oleh dua menu berbeda, lihat Bantuan.
    private String line(LibraryItem item) {
        return "";
    }

    private void showItems() {
        // TODO
    }

    private void addBook() {
        // TODO
    }

    private void addDvd() {
        // TODO
    }

    private void addMagazine() {
        // TODO
    }

    private void registerMember() {
        // TODO
    }

    private void lendItem() {
        // TODO
    }

    private void returnItem() {
        // TODO
    }

    private void searchItems() {
        // TODO
    }

    private void showMembers() {
        // TODO
    }
}
```

`LibraryApp` tidak memakai `System.in` dan `System.out` langsung. Itu yang membuat autograder bisa "mengetik" menu untuk aplikasimu.

### `Main`: satu-satunya titik awal program

Buka `Main.java` dan lanjutkan di bawah baris sapaan. `Main` membuat `Library`, mengisi data contoh, lalu menjalankan `LibraryApp` dengan keyboard dan layar sungguhan. `LibraryApp` tidak punya `main` sendiri.

```java
import java.util.Scanner;   // di bagian atas file

public static void main(String[] args) {
    // TODO: lihat Bantuan di bawah untuk apa yang harus terjadi di sini, lalu tulis sendiri.
}
```

`Main` harus: mencetak sapaan, membuat satu `Library` bernama `Polinema Library`, mengisi data contoh yang terlihat di "Coba Aplikasinya" (satu `Book` dengan penulisnya, satu `Dvd`, satu `Magazine`, satu `Member`), lalu menjalankan `LibraryApp` dengan keyboard dan layar sungguhan.

- Constructor `LibraryApp` meminta tiga hal: `Library`, sumber input, dan tujuan output. Untuk "keyboard sungguhan" dan "layar sungguhan", objek baku Java apa yang kamu pakai, dan bagaimana cara membuatnya?
- Setelah `LibraryApp` dibuat, method apa yang membuat menunya benar-benar berjalan?

<details>
<summary>Bantuan 1: LibraryApp</summary>

- Kerjakan method satu per satu, bukan semuanya sekaligus. Dari sepuluh handler, yang mana paling sederhana, yaitu hanya membaca dari `library` dan mencetak tanpa mengubah apa pun? Urutkan dari situ menuju yang paling banyak langkahnya (`addBook`). Jalankan `P08LibraryAppTest` setelah beberapa method selesai.
- Untuk tiap method, tanya: data apa yang perlu dibaca dari pengguna, method apa di `Library` (atau `Member`/`LibraryItem`) yang menjawab permintaan ini, dan pesan mana dari tabel "Teks yang harus dicetak" yang dicetak untuk tiap kemungkinan hasil?
- Semua handler berpola sama: **baca input**, **periksa**, **serahkan ke `library`**, lalu **cetak hasil**. Apakah `LibraryApp` sendiri yang memutuskan boleh/tidaknya suatu aksi, atau keputusan itu didelegasikan?
- Cetak dengan `out`, baca dengan `readLine` dan `readInt`, bukan `System.out`/`System.in` langsung. Mengapa itu penting untuk tes otomatis? `readInt` mengembalikan -1 untuk input yang bukan bilangan positif. Kapan kamu harus memeriksa -1 itu, sebelum atau sesudah membuat objek?
- Pesan di layar harus persis seperti tabel. Apa akibatnya kalau satu huruf, spasi, atau tanda baca berbeda?
- `line` dipakai oleh dua menu berbeda (lihat tabel: formatnya sama untuk menu 1 dan menu 8). Apa untungnya menulis logikanya satu kali di method ini saja?
- `run` sudah lengkap, baca dulu alurnya. Mengapa perulangan berhenti saat pilihan `0` atau saat input habis, tapi tidak untuk pilihan lain?

</details>

<details>
<summary>Bantuan 2: LibraryApp (langkah per method, buka jika Bantuan 1 belum cukup)</summary>

- **`printMenu`**: Berapa baris yang dicetak dengan pindah baris (`println`), dan baris mana yang tidak (`print`)? Bagian mana dari judul yang berasal dari `library`, bukan teks tetap?
- **`line`**: Satu baris koleksi tersusun dari tiga bagian yang dipisah ` | `: hasil `describe()`, lalu status, lalu lama pinjam dengan akhiran ` days`. Status punya dua kemungkinan teks, method apa di `LibraryItem` yang menentukan yang mana?
- **`showItems`**: Array apa yang diminta dari `library`? Jika kosong, pesan apa yang dicetak? Jika tidak, method `line` dipanggil berapa kali?
- **`addBook`**: Data mana yang dibaca lebih dulu, ISBN/Title/Year atau data penulis? Ada dua kemungkinan kegagalan berbeda (`Invalid number` vs `Invalid input`). Kondisi apa yang memicu masing-masing, dan harus diperiksa sebelum objek `Book` dibuat atau sesudah? Data penulis dibaca hanya dalam satu kondisi, kondisi apa itu?
- **`addDvd`** dan **`addMagazine`**: pola yang sama dengan `addBook` tapi tanpa langkah penulis. Prompt mana yang berubah di masing-masing?
- **`registerMember`**: dua data apa yang dibaca, dan kapan keduanya dianggap tidak valid?
- **`lendItem`** dan **`returnItem`**: method `Library` apa yang dipanggil masing-masing, dan dua pesan apa yang dipilih berdasarkan hasilnya?
- **`searchItems`**: hasil pencarian bisa lebih dari satu atau kosong. Bagaimana format cetaknya dibandingkan dengan `showItems`?
- **`showMembers`**: bentuk satu barisnya `id - nama (N loans)`. Dari method apa di `Member` kamu mendapatkan tiap bagian itu?

</details>

### Teks yang harus dicetak

`Main` mencetak sapaan, membuat `Library` bernama `Polinema Library` berisi data contoh, lalu menjalankan `LibraryApp` dengan `System.in` dan `System.out`.

Tulis output **persis** seperti tabel. Cetak pesan dengan `println` dan prompt dengan `print` (tanpa pindah baris).

| Menu | Prompt (berurutan) | Hasil |
|---|---|---|
| (tiap putaran) | Judul `=== <nama perpustakaan> ===`, lalu baris `1. List items`, `2. Add book`, `3. Add DVD`, `4. Add magazine`, `5. Register member`, `6. Lend item`, `7. Return item`, `8. Search items`, `9. List members`, `0. Exit`, lalu prompt `Choose: ` | menu tampil lagi setelah setiap aksi |
| 1 List items | tidak ada | Satu baris per koleksi: `describe() \| status \| N days`. Status: `Available` atau `Borrowed`. N = `loanDays()`. Jika kosong: `No items`. |
| 2 Add book | `ISBN: `, `Title: `, `Year: `, `Author name (blank for none): `, lalu `Author country: ` hanya jika nama penulis tidak kosong | `Item added`, atau `Library is full` |
| 3 Add DVD | `Title: `, `Year: `, `Duration (minutes): ` | `Item added`, atau `Library is full` |
| 4 Add magazine | `Title: `, `Year: `, `Issue number: ` | `Item added`, atau `Library is full` |
| 5 Register member | `Member ID: `, `Name: ` | `Member registered`, atau `Member ID already exists` |
| 6 Lend item | `Member ID: `, `Title: ` | `Loan successful`, atau `Loan failed` |
| 7 Return item | `Member ID: `, `Title: ` | `Return successful`, atau `Return failed` |
| 8 Search items | `Keyword: ` | Satu baris per hasil (format sama dengan menu 1). Jika tidak ada: `No items found` |
| 9 List members | tidak ada | Satu baris per anggota: `M001 - Budi (1 loans)`. Jika kosong: `No members` |
| 0 Exit | tidak ada | `Goodbye`, lalu program selesai |
| lainnya | tidak ada | `Invalid choice` |

Aturan input untuk menu 2, 3, dan 4:

- Jika `Year` (atau durasi atau nomor edisi) bukan bilangan bulat positif: cetak `Invalid number` dan batalkan penambahan.
- Jika `ISBN` atau `Title` kosong: cetak `Invalid input` dan batalkan.
- Untuk menu 5, jika `Member ID` atau `Name` kosong: cetak `Invalid input` dan batalkan.
- Kamu **tidak** perlu menangkap exception. Cek dulu inputnya, sehingga constructor tidak pernah menerima data tidak valid.
- Untuk menu 2: `ISBN`, `Title`, dan `Year` dibaca dulu. Data penulis dibaca setelah ketiganya valid.

Contoh baris menu 1: `Clean Code (2008) by Robert Martin | Available | 14 days`.

### Uji Pemahaman

Tidak dinilai: ikuti satu alur peminjaman dari menu 6 sampai selesai. Sebutkan urutan objek yang dipanggil: `LibraryApp`, `Library`, `Member`, `LibraryItem`. Method apa saja yang berjalan di setiap objek? Tulis di laporan.

### Periksa Hasil

Tes yang harus lulus: `P08LibraryTest` dan `P08LibraryAppTest`. Setelah itu jalankan aplikasi dan coba semua menu.

---

## Coba Aplikasinya

Setelah `Main` selesai, jalankan:

```
mvn -q compile exec:java
```

Contoh sesi (data awal: buku `Clean Code`, DVD `Inception`, majalah `Tempo`, anggota `M001 - Budi`):

```
=== Polinema Library ===
1. List items
...
0. Exit
Choose: 6
Member ID: M001
Title: Clean Code
Loan successful
...
Choose: 1
Clean Code (2008) by Robert Martin | Borrowed | 14 days
Inception (2010) [148 min] | Available | 3 days
Tempo (2024) | Available | 7 days
...
Choose: 9
M001 - Budi (1 loans)
...
Choose: 0
Goodbye
```

## Laporan (PDF)

Serahkan satu file PDF: `laporan/Laporan_<NIM>_<Nama>.pdf`, contoh `laporan/Laporan_2441720001_Budi_Santoso.pdf`. Commit dan push ke repositori. Tulis dengan kata-katamu sendiri. Laporan salinan tidak dinilai.

Isi laporan:

1. **Identitas**: nama, NIM, kelas, link repositori GitHub.
2. **Setiap Bagian 1 sampai 7**:
   - Penjelasan konsep dengan kata-katamu sendiri (3 sampai 5 kalimat): apa itu, mengapa dipakai, di kelas mana kamu memakainya. Jawab juga pertanyaan di bagian "Pikirkan Dulu".
   - Potongan kode buatanmu (5 sampai 15 baris) dan penjelasannya baris per baris.
   - Jawaban "Uji Pemahaman".
   - Kendala dan solusi. Tulis Bantuan yang kamu buka.
3. **Tambahan Bagian 7**:
   - Screenshot aplikasi berjalan (minimal satu sesi pinjam dan kembali).
   - Penjelasan kerja sama `LibraryApp`, `Library`, `Member`, dan `LibraryItem` pada satu alur peminjaman.
4. **Hasil pengujian**: screenshot `mvn test` atau tab Actions yang menampilkan nilai.
5. **Bukti WakaTime**: screenshot dashboard project `oop-library` (total waktu dan grafik per hari) dan total jam dalam teks.
6. **Refleksi**: konsep yang masih membingungkan dan rencana belajarmu.

## Pengumpulan

Kumpulkan **seluruh repositori**. Sebelum batas waktu, pastikan:

- [ ] WakaTime aktif selama kamu mengerjakan, dan project `oop-library` muncul di dashboard.
- [ ] Semua kode sudah di-push.
- [ ] Autograder di tab Actions sudah berjalan dan menampilkan nilai.
- [ ] `laporan/Laporan_<NIM>_<Nama>.pdf` ada, bernama benar, dan sudah di-push.
- [ ] Laporan memuat screenshot WakaTime.
- [ ] `.wakatime-project` tidak berubah.

Kirim link repositori sesuai arahan Dosen.

## Penilaian

Autograder menilai kode (100 poin). Dosen menilai laporan dan usaha (WakaTime) secara manual. **Ketiganya wajib ada.**

| Tes | Bagian | Poin |
|---|---|---|
| `P01MainOutputTest` | 1 | 5 |
| `P02AuthorTest` | 2 | 5 |
| `P02BookTest` | 2 | 10 |
| `P03EncapsulationTest` | 3 | 10 |
| `P03MemberTest` | 3 | 10 |
| `P04AssociationTest` | 4 | 5 |
| `P04ShelfAggregationTest` | 4 | 10 |
| `P04MemberCardCompositionTest` | 4 | 5 |
| `P04LibrarianDependencyTest` | 4 | 5 |
| `P06InheritanceTest` | 5 | 10 |
| `P07OverrideTest` | 6 | 10 |
| `P07OverloadTest` | 6 | 5 |
| `P08LibraryTest` | 7 | 5 |
| `P08LibraryAppTest` | 7 | 5 |
| **Total** | | **100** |

Satu kelas tes dinilai penuh hanya jika **semua** tesnya lulus.
