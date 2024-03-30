package com.example.starlingtest.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.starlingtest.entity.TransactionFeed;

@Service
public class RoundupHelper {
	 public BigDecimal roundUpTransactionAmount(final List<TransactionFeed> transactions)
	    {
	        BigDecimal rounding = BigDecimal.ZERO;

	        for(TransactionFeed transaction : transactions)
	        {
	            final BigDecimal amount = transaction.getBalance().getMinorUnits();

	            if(!amount.equals(BigDecimal.ZERO) && transaction.getDirection().equals("OUT"))
	            {
	                rounding = rounding.add(this.round(transaction));
	            }
	        }

	        return rounding;
	    }

	    private BigDecimal round(final TransactionFeed transaction)
	    {
	        final BigDecimal amount = transaction.getBalance().getMinorUnits();
	        final BigDecimal oneHundred = new BigDecimal(100);
	        final BigDecimal decimalAmount = amount.divide(oneHundred, 2, RoundingMode.UNNECESSARY);

	        if(decimalAmount.equals(BigDecimal.ZERO))
	        {
	            return BigDecimal.ZERO;
	        }

	        final BigDecimal fractionalPart = decimalAmount.remainder(BigDecimal.ONE);
	        return BigDecimal.ONE.subtract(fractionalPart);
	    }
}
