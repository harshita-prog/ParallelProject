package com.cg.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cg.project.bean.Account;
import com.cg.project.exception.AccountException;
import com.cg.project.service.AccountService;
import com.cg.project.service.TransactionService;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;



	@PostMapping("/transactions")
	public String getAllTransactions( @RequestParam(value = "id", required = true)int id, Model model ) {
		List<String> transactions = transactionService.getTransaction(id);
		model.addAttribute("transactions", transactions);
		return "transactionList"; // Thymeleaf template name
	}
@GetMapping("/transactions")
	public String transactionList(){
	return 	"transactionList";
}


	@GetMapping("/login")
	public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
		if ("true".equals(error)) {
			model.addAttribute("errorMessage", "User does not exist or incorrect password.");
		}
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(@RequestParam String username, @RequestParam String password) throws AccountException {
		// Check if the username and password exist in your user repository
		Account user = accountService.getAccountDetailsByUsername(username, password);

		if (user != null) {
			String successMessage = "logged in successfully";
			// User exists, you can set up a session here
			return "redirect:/menu?successMessage=" + successMessage;		} else {
			return "redirect:/login?error=true"; // Redirect to log in with error query parameter
		}
	}


	@GetMapping("/{username}/{password}")
	public Account getAccountDetailsByUsername(@PathVariable String username, @PathVariable String password) throws AccountException {
		return accountService.getAccountDetailsByUsername(username, password);
	}

	@GetMapping("/update")
	public String updateAccountForm() {
		return "updateAccountForm";
	}

	@PostMapping("/update")
	public String updateAccount(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) throws AccountException {
		if (bindingResult.hasErrors()) {
			return "updateAccountForm";
		}
		// Update the account data in the database or perform other actions
		accountService.updateDetials(account);
		return "redirect:/api/account/update"; // Redirect to the update form
	}

	@GetMapping("/transfer")
	public String fundTransferForm() {
		return "fundTransferForm";
	}

	@PostMapping("/transfer")
	public String fundTransfer(@RequestParam int accountNo1, @RequestParam int accountNo2, @RequestParam int amount) throws AccountException {
		transactionService.addTransaction("Amount " + amount + " deducted from account and added to account with id " + accountNo2 + ".", accountNo1);
		accountService.fundTransferUpdate(accountNo1, accountNo2, amount);
		String successMessage = "Fund transferred successfully";
		return "redirect:/menu?successMessage=" + successMessage;
	}
	@GetMapping("/withdraw")
	public String withdrawMoneyForm() {
		return "withdrawMoneyForm";
	}

	@PostMapping("/withdraw")
	public String withdrawMoney(@RequestParam int accnumber, @RequestParam int amount) throws AccountException {
		transactionService.addTransaction("Amount " + amount + " Withdrawn from your account", accnumber);
		accountService.withdrawMoney(accnumber, amount);
		String successMessage = "Amount withdrawn successfully";


		return "redirect:/menu?successMessage=" + successMessage;
	}

	@GetMapping("/deposit")
	public String depositMoneyForm() {
		return "depositMoneyForm";
	}

	@PostMapping("/deposit")
	public String depositMoney(@RequestParam int accnumber, @RequestParam int amount) throws AccountException {
		transactionService.addTransaction("Amount " + amount + " added to your account", accnumber);
		accountService.depositMoney(accnumber, amount);
		String successMessage = "Amount deposited successfully";
		return "redirect:/menu?successMessage=" + successMessage; // Redirect to the deposit form
	}


	@PostMapping("/accnumber")
	public String getAccountDetailsByAccountNumber(@RequestParam int accnumber, Model model) throws AccountException {
		Account account = accountService.getAccountDetailsByAccountNumber(accnumber);
		model.addAttribute("account", account);
		return "accountDetails"; // Corresponds to accountDetails.html
	}




	@GetMapping("/createAccount")
	public String createAccount(Model model) {
		model.addAttribute("account", new Account());
		return "createAccount";
	}

	@PostMapping("/createAccount")
	public String createAccount(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) throws AccountException {
		if (bindingResult.hasErrors()) {
			return "createAccount";
		}
		// Save the account data to the database or perform other actions
		accountService.addAccount(account);
		String successMessage = "Account created successfully";

		// Redirect to the menu page with the success message as a parameter
		return "redirect:/menu?successMessage=" + successMessage;
	}


}
