import urllib

currencies = ['USD', 'BGN', 'EUR', 'GBP']
rates = {}

for i, c in enumerate(currencies):
	exchange = currencies[:]
	exchange.pop(i)
	rates[c] = exchange

yahoo_exchange = []
for c in rates:
	for e in rates[c]:
		yahoo_exchange.append("&s=%s%s=X" % (c, e))

exchange_query = ''.join(yahoo_exchange)
exchange_url = 'http://download.finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1%s' % exchange_query

exchange_request = urllib.urlopen(exchange_url)

print exchange_request.read()
