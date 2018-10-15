select E.EAN AS EAN_PRODUKTU,S.STAN AS ILOSC_PRODUKTU
FROM STANMAG S
INNER JOIN KARTOTEKA K ON S.id_kartoteka = K.ID_KARTOTEKA
INNER JOIN MAGAZYN M  ON M.ID_MAGAZYN = S.ID_MAGAZYN
INNER JOIN EAN E ON E.id_kartoteka = S.id_kartoteka


[

  {

    "ean": "EAN_PRODUKTU",

    "quantity": "ILOSC PRODUKTU"

  },

  {

    "ean": "142395234945",

    "quantity": "154"

  },

  ...

]