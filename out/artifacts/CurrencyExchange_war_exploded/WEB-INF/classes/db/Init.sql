CREATE TABLE Currencies (
                            ID INTEGER PRIMARY KEY AUTOINCREMENT,
                            Code VARCHAR(10) NOT NULL UNIQUE,
                            FullName VARCHAR(20) NOT NULL,
                            Sign VARCHAR(10) NOT NULL
);

CREATE TABLE ExchangeRates (
                               ID INTEGER PRIMARY KEY AUTOINCREMENT,
                               BaseCurrencyId INT  NOT NULL UNIQUE,
                               TargetCurrencyId INT NOT NULL UNIQUE,
                               Rate Decimal(6) NOT NULL,
                               FOREIGN KEY (BaseCurrencyId) REFERENCES Currencies(ID),
                               FOREIGN KEY (TargetCurrencyId) REFERENCES Currencies(ID)
);