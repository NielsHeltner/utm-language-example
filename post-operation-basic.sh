curl --location --request POST 'http://localhost:7000/api/operations/basic' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "uas": {
    "uuid": "string",
    "takeOffPosition": {
      "lat": 55.0,
      "lon": 10.0
    }
  },
  "points": [
    {
      "lat": 56.0,
      "lon": 10.0
    }, 
    {
       "lat": 56.0, 
       "lon": 11.0
    }
  ]
}'