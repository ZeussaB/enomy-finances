/**
 * 
 */
// Define exchange rates with GBP as the base currency
const exchangeRates = {
    "GBP": 1, // GBP to GBP
    "USD": 1.24, // USD to GBP
    "EUR": 1.14, // EUR to GBP
    "BRL": 7.55, // BRL to GBP
    "JPY": 160.12, // JPY to GBP
    "TRY": 23.75 // TRY to GBP
};

// Function to calculate the fee
function calculateFee(amount) {
    if (amount <= 500) {
        return amount * 0.035;
    } else if (amount <= 1500) {
        return amount * 0.027;
    } else if (amount <= 2500) {
        return amount * 0.02;
    } else {
        return amount * 0.015;
    }
}

// Function to perform the currency conversion
function convertCurrency() {
    const fromCurrency = document.getElementById('fromCurrency').value;
    const toCurrency = document.getElementById('toCurrency').value;
    const amount = parseFloat(document.getElementById('amount').value);

    if (isNaN(amount) || amount < 300 || amount > 5000) {
        alert("Please enter a valid amount between 300 and 5000.");
        return;
    }

    // Calculate the fee
    const fee = calculateFee(amount);
    const amountAfterFee = amount - fee;

    // Convert the 'fromCurrency' to GBP
    const amountInGBP = amountAfterFee / exchangeRates[fromCurrency];

    // Convert the amount from GBP to the 'toCurrency'
    const convertedAmount = amountInGBP * exchangeRates[toCurrency];

    // Update JSP elements with results
    document.getElementById('originalAmount').textContent = `${amount.toFixed(2)} ${fromCurrency}`;
    document.getElementById('feeAmount').textContent = `${fee.toFixed(2)} ${fromCurrency}`;
    document.getElementById('amountAfterFee').textContent = `${amountAfterFee.toFixed(2)} ${fromCurrency}`;
    document.getElementById('convertedAmount').textContent = `${convertedAmount.toFixed(2)} ${toCurrency}`;
}

// Event listener for form submission
document.getElementById('currencyForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form submission
    convertCurrency(); // Call the function to convert the currency
});