package com.example.starlingtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.starlingtest.entity.Amount;
import com.example.starlingtest.entity.SavingGoal;
import com.example.starlingtest.entity.TransactionFeed;
import com.example.starlingtest.exceptions.StarlingBankException;
import com.example.starlingtest.service.RoundUpBalanceService;
import com.example.starlingtest.service.SavingGoalService;
import com.example.starlingtest.service.TransactionService;
import com.example.starlingtest.utils.RoundupHelper;

@SpringBootTest
class RoundUpApplicationTests {

	private final String ACCOUNT_ID = "test-account-id";
	private final String SAVINGS_GOAL_ID = "test-savings-goal-id";
	private final Amount testAmount = new Amount("GBP", new BigDecimal("1321"));
	private final Amount testAmount2 = new Amount("GBP", new BigDecimal("3631"));
	private final Amount testAmount3 = new Amount("GBP", new BigDecimal("1971"));
	private final Amount testAmount4 = new Amount("GBP", new BigDecimal("3421"));

	private final List<TransactionFeed> mockTransactionList = new ArrayList<>();

	@Mock
	private RoundUpBalanceService roundUpBalanceServiceMock;
	@Mock
	private TransactionService transactionServiceMock;
	@Mock
	private RoundupHelper roundupHelperMock;
	@Mock
	private SavingGoalService savingGoalServiceMock;

	@Test
	void shouldReturnAccountIdWhenCallingGetAccountUid() throws StarlingBankException {
		when(roundUpBalanceServiceMock.getAccountUid()).thenReturn(ACCOUNT_ID);
		assertEquals(ACCOUNT_ID, roundUpBalanceServiceMock.getAccountUid());
	}

	@Test
	void shouldReturnListOfEmptyTransactionsWhenThereAreNoTransactions() throws StarlingBankException {
		when(transactionServiceMock.getTransactions()).thenReturn(mockTransactionList);
		assertEquals(Collections.emptyList(), transactionServiceMock.getTransactions());
	}

	@Test
	void shouldReturnListOfTransactionsWhenThereAreTransactionsPresent() throws StarlingBankException {
		when(transactionServiceMock.getTransactions()).thenReturn(mockTransactionList);
		mockTransactionList.add(new TransactionFeed(null, testAmount, null, null));

		List<TransactionFeed> expected = new ArrayList<>();
		expected.add(new TransactionFeed(null, testAmount, null, null));

		assertEquals(expected, transactionServiceMock.getTransactions());
	}

	@Test
	void shouldReturnZeroRoundUpAmountWhenTransactionListIsEmpty() {
		when(roundupHelperMock.roundUpTransactionAmount(Collections.emptyList())).thenReturn(new BigDecimal(0));
		assertEquals(new BigDecimal(0), roundupHelperMock.roundUpTransactionAmount(new ArrayList<>()));
	}

	@Test
	void shouldReturnRoundUpAmountWhenTransactionListIsNotEmpty() {
		when(roundupHelperMock.roundUpTransactionAmount(mockTransactionList)).thenCallRealMethod();

		mockTransactionList.add(new TransactionFeed(null, testAmount, "OUT", null));
		mockTransactionList.add(new TransactionFeed(null, testAmount2, "OUT", null));
		mockTransactionList.add(new TransactionFeed(null, testAmount3, "OUT", null));
		mockTransactionList.add(new TransactionFeed(null, testAmount4, "IN", null));

		BigDecimal expected = new BigDecimal(1.77).setScale(2, RoundingMode.DOWN);

		assertEquals(expected, roundupHelperMock.roundUpTransactionAmount(mockTransactionList));
	}

	@Test
	void shouldReturnEmptyListWhenThereAreNoSavingsGoals() throws StarlingBankException {
		when(savingGoalServiceMock.getSavingGoalsList()).thenReturn(Collections.emptyList());
		assertEquals(Collections.emptyList(), savingGoalServiceMock.getSavingGoalsList());
	}

	@Test
	void shouldCreateNewSavingsGoalWhenThereAreNoSavingsGoals() throws StarlingBankException {
		when(savingGoalServiceMock.createNewSavingsGoal()).thenReturn(SAVINGS_GOAL_ID);
		assertEquals(SAVINGS_GOAL_ID, savingGoalServiceMock.createNewSavingsGoal());
	}

	@Test
	void shouldReturnListWhenThereIsASavingGoal() throws StarlingBankException {
		List<SavingGoal> savingGoalListToReturn = new ArrayList<>();
		savingGoalListToReturn.add(new SavingGoal());

		when(savingGoalServiceMock.getSavingGoalsList()).thenReturn(savingGoalListToReturn);

		assertEquals(1, savingGoalServiceMock.getSavingGoalsList().size());
	}

	@Test
	void shouldReturnSavingsGoalListFromRoundUpServiceRoundUp() {
		when(roundUpBalanceServiceMock.roundUpTransactionsFeed()).thenReturn(Collections.emptyList());
		assertEquals(Collections.emptyList(), roundUpBalanceServiceMock.roundUpTransactionsFeed());
	}
}
