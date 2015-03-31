package lefrage

class Stat {
	Date date
	float mean, deviation, max, min
	int sales

	static belongsTo=[query: Query]
		
	Stat calcMean(prices) { 
		def n = prices.size()
		mean = prices.sum() / n
		return this
	}

	Stat calcDeviation(prices) {
		def pricesSquared = prices.collect { it**2 }
		deviation = Math.sqrt( pricesSquared.sum()/pricesSquared.size() - (prices.sum()/prices.size())**2)
		return this
	}

	Stat calcMax(prices) {
		max = prices.max()
		return this
	}

	Stat calcMin(prices) {
		min = prices.min()
		return this
	}

    static constraints = {
    	mean nullable:true
    	deviation nullable:true
    	max nullable:true
    	min nullable:true
    	sales nullable:true
    }

}
