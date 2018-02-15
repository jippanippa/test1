# Тестовое задание

Задача: создать приложение отвечающее следующим параметрам

1) На главном активити должен отображаться список новостей

2) Должна быть предусмотрена навигация по видам спорта

3) При нажатии на новость - она должна открываться в отдельном активити с подробностями

4) Все ошибки сети и запросов к серверу должны обрабатываться корректно с выводом тостов

5) Бонусом будет приятное визуальное оформление элементов приложения

API

Для получения информации нужно отправить GET запрос

1) Получить список всех статей:
http://mikonatoruri.win/list.php?category={category}

Допустимые значения категорий - football, hockey, tennis, basketball, volleyball, cybersport

в ответ придет следующий JSON (Пример)
```json
{
    "events": [
        {
            "title": "«Жирона» – «Леганес»: как закончится игра?",
            "coefficient": "1.98 Коэффициент ",
            "time": "Время: Завтра в 23:00 МСК",
            "place": "Турнир: Испания. Примера",
            "preview": "В стартовом матче 24-го тура испанской Примеры«Жирона» сыграет с «Леганесом». В первом круге соперники сыграли вничью 0:0. Будет ли определен победитель в этой встрече? Предлагаем наш прогноз. ",
            "article": "/2018/02/15/zhirona-leganes-prognoz-na-ispanskuju-la-ligu-16-02-2018"
        },
        {
            "title": "«Лестер» – «Шеффилд Юнайтед»: кто пройдет дальше?",
            "coefficient": "2.02 Коэффициент ",
            "time": "Время: Завтра в 22:45 МСК",
            "place": "Турнир: Кубок Англии",
            "preview": "В пятницу в матче 1/8 финала Кубка Англии «Лестер» сыграет с «Шеффилд Юнайтед». Регламент турнира предполагает только один поединок, поэтому вряд ли в планы хозяев входит переигровка. Удастся ли «лисам» выиграть поединок? Предлагаем наш прогноз....",
            "article": "/2018/02/15/lester-sheffild-junajted-prognoz-na-kubok-anglii-16-02-2018"
        }
    ]
}
```

2) Получить статью:
http://mikonatoruri.win/post.php?article={article}

В article нужно подставить полученное значение article из списка всех статей (например "/2018/02/15/lester-sheffild-junajted-prognoz-na-kubok-anglii-16-02-2018")

в ответ придет следующий JSON (Пример)

```json
{
    "team1": "«Жирона» ",
    "team2": "«Леганес»",
    "time": "Завтра в 23:00 МСК",
    "tournament": "Испания. Примера",
    "place": "Стадион \"Монтиливи\", Жирона",
    "article": [
        {
            "header": "«Жирона» ",
            "text": "«Жирона» в минувшем туре играла на выезде против «Севильи». Поединок был важен в плане шансов каталонского клуба вмешаться в распределение путевок в еврокубки. Как оказалось, пока еще «жиронцам» рановато на международную арену. Единственный точный удар «нервионцев» в конце первого тайма оказался победным.Поражение от «Севильи» прервало неплохую серию подопечных Пабло Мачина, на протяжении которой они сыграли на выезде вничью с мадридским «Атлетико» 1:1 и «Малагой» 0:0, а также одержали две домашние победы над «Лас-Пальмасом» 6:0 и «Атлетиком» Бильбао 2:0. В целом за шесть предыдущих домашних поединков «Жирона» уступила только «Алавесу» 2:3. В тоже время к выше указанным победам можно также добавить за этот период успех в матчах с мадридским «Реалом» 3:2 и «Хетафе» 1:0.В лазарете у каталонцев Хосе Аурелио Суарес, Пере Понс и Дуглас Луис."
        },
        {
            "header": "«Леганес»",
            "text": "«Леганес» в турнирной таблице занимает 12-е место, отставая от десятой «Жироны» на два очка, хотя и сыграл на одну встречу меньше.«Леганес» очень неплохо выступал в национальном Кубке, где завершил выступление на стадии полуфинала. Игра на двух фронтах сказалась на результатах команды в январе. В пяти последних турах «лесники» одержали всего одну победу – дома над «Эспаньолом» 3:2. Кроме этого два поединка завершили вничью – с «Алавесом» и «Хетафе», причем оба на выезде, а также уступили «Бетису» и «Эйбару». Хотя в целом на выезде «Леганес» играет слабо. На полях соперников подопечные Асьера Гаритано не побеждали уже восемь матчей подряд, уступив в пяти из них.Не сыграет из-за дисквалификации Димитриос Сиовас. В лазарете Александр Шимановски и Мауро дос Сантос."
        },
        {
            "header": "Статистика и личные встречи",
            "text": " • «Жирона» выиграла дома в Примере три последних матча подряд, не пропустив ни гола.\n • «Леганес» проиграл в чемпионате пять из восьми предыдущих гостевых поединков.\n • В пяти последних матчах «Жироны» в Примере забивала только одна команда.\n • Из 12 гостевых встреч «Леганеса» в девяти забивала только одна команда.\n • Три последних очных поединка между соперниками завершились вничью.\n"
        }
    ],
    "prediction": " «Жирона» в родных стенах редко теряла очки в последнее время  – четыре победы и одна ничья в шести последних встречах, причем  и пропустили в пяти предыдущих домашних поединках каталонцы только дважды. «Леганес» слабо играет в гостях, к тому ни разу в истории не удалось обыграть «жиронцев» на их поле."
}
```
