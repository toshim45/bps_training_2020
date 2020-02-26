# bps_training_2020


## Flow Aplikasi

svc1: productsurvey
svc2: employee
browser1: survey-dashboard

### Flow Reward karyawan
1. create survey 
2. ketika survey dibuat akan mentrigger reward untuk employee (ini via rabbitmq)

### Flow Laporan statistik mean barang
1. create survey
2. ketika survey dibuat makan svc2 akan menghitung statistik mean dari barang yg di survey
3. hasil dari no 2 akan ditampilkan di browser menggunakan socket via stomp

