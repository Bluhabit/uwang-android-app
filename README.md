# UWANG

# DATASOURCE
Data source merupakan layer dari mana data pada aplikasi bersumber umumnya terdapat 2 datasource:
1. Remote - sumber data berasal dari backend
2. Local - sumber data lokal atau database local seperti sqllite,atau pun lainnya yang sifatnya persistance pada perangkat

# REPOSITORY
Repository merupakan tempat dimana perputaran data, sebagai contoh
ketika aplikasi membutuhkan data dari backend dan menyimpan ke database lokal maka repository lah yang memiliki tugas dan tanggung jawab tersebut


# DOMAIN
Domain merupakan penguhubung antara persentation(UI) dengan data(Repository) karena repository hanya memiliki tugas untuk perputaran data pada perangkat 
dan terkadang pada beberapa fitur kita perlu memakai lebih dari 1 repository.


# PRESENTATION
Presentation merupakan layer yang berhubungan dengan interaksi aplikasi dengan user yaitu meliputi UI(Halaman,Tampilan),ViewModel
