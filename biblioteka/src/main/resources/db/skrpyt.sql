CREATE TABLE Autorzy (
      ID int(11) NOT NULL AUTO_INCREMENT,
      imie varchar(25) DEFAULT NULL,
      nazwisko varchar(25) DEFAULT NULL,
      PRIMARY KEY (ID)
)

CREATE TABLE Ksiazki (
      ID int(11) NOT NULL,
      tytul varchar(50) DEFAULT NULL,
      kategoria varchar(10) DEFAULT NULL,
      stan int(11) DEFAULT NULL,
      IDAutora int(11) DEFAULT NULL,
      ilosc int(11) DEFAULT NULL,
      wypozyczenia int(11) DEFAULT NULL,
      PRIMARY KEY (ID),
      KEY autor (IDAutora),
      KEY tytul (tytul),
      CONSTRAINT autor FOREIGN KEY (IDAutora) REFERENCES Autorzy (ID)
)

CREATE TABLE Pozycje (
      ID int(11) NOT NULL AUTO_INCREMENT,
      polka int(11) DEFAULT NULL,
      dostepnosc int(11) DEFAULT NULL,
      IDKsiazki int(11) DEFAULT NULL,
      dzial varchar(3) DEFAULT NULL,
      PRIMARY KEY (ID),
      KEY ksiazka (IDKsiazki),
      CONSTRAINT ksiazka FOREIGN KEY (IDKsiazki) REFERENCES Ksiazki (ID)
)

CREATE TABLE USERS (
      ID int(11) NOT NULL AUTO_INCREMENT,
      login varchar(25) DEFAULT NULL,
      haslo varchar(25) DEFAULT NULL,
      imie varchar(25) DEFAULT NULL,
      nazwisko varchar(25) DEFAULT NULL,
      level int(11) DEFAULT NULL,
      PRIMARY KEY (ID),
      KEY userLogin (login)
)

CREATE TABLE Wiadomosci (
      ID int(11) NOT NULL AUTO_INCREMENT,
      data date DEFAULT NULL,
      tytul varchar(255) DEFAULT NULL,
      tresc varchar(10000) DEFAULT NULL,
      IDCzytelnika int(11) DEFAULT NULL,
      PRIMARY KEY (ID),
      KEY Wczytelnik (IDCzytelnika),
      KEY dataWiadomosci (data),
      CONSTRAINT Wczytelnik FOREIGN KEY (IDCzytelnika) REFERENCES USERS (ID)
)

CREATE TABLE Wypozyczenia (
      ID int(11) NOT NULL AUTO_INCREMENT,
      dataStart date DEFAULT NULL,
      dataEnd date DEFAULT NULL,
      IDPozycji int(11) DEFAULT NULL,
      IDCzytelnika int(11) DEFAULT NULL,
      PRIMARY KEY (ID),
      KEY WypCzytelnik (IDCzytelnika),
      KEY In_IDPOZYCJI (IDPozycji),
      CONSTRAINT WypCzytelnik FOREIGN KEY (IDCzytelnika) REFERENCES USERS (ID),
      CONSTRAINT WypPozycja FOREIGN KEY (IDPozycji) REFERENCES Pozycje (ID)
)

CREATE TABLE `Zamowienia` (
      `ID` int(11) NOT NULL AUTO_INCREMENT,
      `data` date DEFAULT NULL,
      `stan` int(11) DEFAULT NULL,
      `IDCzytelnika` int(11) DEFAULT NULL,
      `IDKsiazki` int(11) DEFAULT NULL,
      `kolejka` int(11) NOT NULL,
      PRIMARY KEY (`ID`),
      KEY `ZKsiazka` (`IDKsiazki`),
      KEY `In_IDCzytelnika` (`IDCzytelnika`),
      CONSTRAINT `ZCzytelnik` FOREIGN KEY (`IDCzytelnika`) REFERENCES `USERS` (`ID`),
      CONSTRAINT `ZKsiazka` FOREIGN KEY (`IDKsiazki`) REFERENCES `Ksiazki` (`ID`)
)

CREATE TABLE Kolejkowanie (
      ID int(11) NOT NULL,
      kolejnosc int(11) DEFAULT NULL,
      PRIMARY KEY (ID),
      CONSTRAINT kID FOREIGN KEY (ID) REFERENCES Zamowienia (ID)
)
