import requests

url = "https://makeup.p.rapidapi.com/products.json"

querystring = {"brand":"colourpop","product_category":"lipstick"}

headers = {
	"X-RapidAPI-Key": "cb84e1853amsh36bd127c21f5c41p12163cjsn5ce359cf2f1a",
	"X-RapidAPI-Host": "makeup.p.rapidapi.com"
}

response = requests.get(url, headers=headers, params=querystring)

print(response.json())