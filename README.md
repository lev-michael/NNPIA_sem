# NNPIA semestrální práce - backend
Backendová aplikace semestrální práce pro předmět NNPIA obsahující zdrojové kódy psané v technologie Java Spring Boot.

## Datové schéma

Datové schéma se skládá z hlavních tabulek:
- *MOVIE*- informace o filmech
- *PERSON*- informace o hercích či členech štábu
- *USERS*- informace o uživatelích
- *GENRE*- informace o žánrech

a následně doplňující tabulky:
- *RATING*- informace o hodnocení filmů jednotlivými uživateli
- *WATCHLIST*- informace o filmech, které jsou součástí seznamu, toho, co chtějí vidět
- *MOVIE_GENRES* - informace o žánrech jednotlivých filmů
- *MOVIE_CAST* - informace, kteří herci hrají v kterých filmech
- *MOVIE_CREW* - informace, kteří další lidé se podíleli na výrobě kterých filmech


![alt text](https://github.com/lev-michael/NNPIA_sem/blob/master/schema.png?raw=true)

## Autorizace

REST API je pro zasílání metod POST zabezpečeno autentizačním procesem vyžadujícím společně s dotazem zaslat i autentizační Bearer token. Tento token je platný po vymezený časový interval a
je k dispozici pro registrované uživatele po jejich přihlášení.

Pro získání tokenu je potřeba zaslat dotaz POST na https://nnpia-sem-backend.herokuapp.com/authenticate s parametry

```
{

  "username": "<uživatelské jméno>",
  
  "password": "<heslo>"
  
}
```

API po úspěšně autorizaci následně vrací požadovaný token.

## Struktura dotazů

Odpověď z API je zabalena do struktury popisující, v jakém stavu byl požadavek na serveru vyřízen.

code - HTTP stavový kód pod kterým server odpověď vyřídil

status - Textový řetězec identifikující odpověď druh vrácených dat

result - Strukturovaná data vrácena serverem (mohou být NULL)

Ukazková odpověd na dotaz na film s id 100 (./movie/100)
```
{
  "status": 200,
  "status_key": "SUCCESS",
  "result": {
     "id": 100,
        "title": "Lock, Stock and Two Smoking Barrels",
        "description": "A card sharp and his unwillingly-enlisted friends need to make a lot of cash quick after losing a sketchy poker match. To do this they decide to pull a heist on a small-time gang who happen to be operating out of the flat next door.",
        "img": "/8kSerJrhrJWKLk1LViesGcnrUPE.jpg",
        "runtime": 105,
        "avgScore": 8.0,
        "release_date": 888966000000,
        "genres": [
            "Comedy",
            "Crime"
        ],
        "crew": [
            {
                "name": "Guy Ritchie",
                "id": 956,
                "img": "/9pLUnjMgIEWXi0mlHYzie9cKUTD.jpg",
                "role": "Director"
            },
            {
                "name": "Guy Ritchie",
                "id": 956,
                "img": "/9pLUnjMgIEWXi0mlHYzie9cKUTD.jpg",
                "role": "Casting"
            },
            {
                "name": "Guy Ritchie",
                "id": 956,
                "img": "/9pLUnjMgIEWXi0mlHYzie9cKUTD.jpg",
                "role": "Screenplay"
            }
        ]
    }
  }
}
```
Při dotazování na seznamy je k dispozici automaticky stránkování. Při volání vrací také doplňující informace např. o celkovém počtu záznamů, celkovém počtu stránek, zda je stránka první či poslední atd.
Implicitní nastavení při volání je 20 položek na stránku. Lze si však volání upravit pomocí parametrů ve volané url. 
Např:
- `page=<cislo_stranky>`
- `size=<pocet_polozek_na_strance>`
- `sort=<atribut_podle_ktereho_radit>,<[asc|desc]>`

Pro volání seznamů je také k dispozici vyhledávání pomocí zaslání (není povinné, při zaslání prázdného body se vrací všechny výsledky):
```
{
  "query": <vyhledavaci_fraze>
}
```

Ukázková odpověď se stránkováním:
```
{
    "code": 200,
    "status": "SUCCESS",
    "result": {
        "content": [
            {
                "id": 414906,
                "avgScore": 9.5,
                "release_date": 1646089200000,
                "title": "The Batman",
                "img": "/74xTEgt7R36Fpooo50r9T25onhq.jpg"
            },
            {
                "id": 634649,
                "avgScore": 8.5,
                "release_date": 1639522800000,
                "title": "Spider-Man: No Way Home",
                "img": "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
            },
            {
                "id": 187,
                "avgScore": 8.0,
                "release_date": 1112220000000,
                "title": "Sin City",
                "img": "/i66G50wATMmPrvpP95f0XP6ZdVS.jpg"
            },
            {
                "id": 106,
                "avgScore": 8.0,
                "release_date": 550274400000,
                "title": "Predator",
                "img": "/wMq9kQXTeQCHUZOG4fAe5cAxyUA.jpg"
            },
            {
                "id": 425,
                "avgScore": 7.5,
                "release_date": 1015628400000,
                "title": "Ice Age",
                "img": "/gLhHHZUzeseRXShoDyC4VqLgsNv.jpg"
            }
        ],
        "pageable": {
            "sort": {
                "unsorted": false,
                "sorted": true,
                "empty": false
            },
            "offset": 0,
            "pageSize": 5,
            "pageNumber": 0,
            "paged": true,
            "unpaged": false
        },
        "totalPages": 62,
        "totalElements": 309,
        "last": false,
        "size": 5,
        "number": 0,
        "sort": {
            "unsorted": false,
            "sorted": true,
            "empty": false
        },
        "numberOfElements": 5,
        "first": true,
        "empty": false
    }
}
```







