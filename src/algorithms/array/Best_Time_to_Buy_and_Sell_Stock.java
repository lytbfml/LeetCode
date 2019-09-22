package algorithms.array; /**
 * @author lytbf on 10/24/2018.
 */

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one
 */
class Best_Time_to_Buy_and_Sell_Stock {
	
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length == 0) {
			return 0;
		}
		
		int profit = 0;
		int minP = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			if(prices[i] < minP) {
				minP = prices[i];
			} else {
				profit = (prices[i] - minP) > profit ? (prices[i] - minP) : profit;
			}
		}
		
		return profit;
	}
	
	public int maxProfit_Sol(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for(int i=0; i<prices.length; i++)
		{
			if(prices[i] < minPrice)
				minPrice = prices[i];
			else if(prices[i] - minPrice > maxProfit)
				maxProfit = prices[i] - minPrice;
		}
		return maxProfit;
	}
}
