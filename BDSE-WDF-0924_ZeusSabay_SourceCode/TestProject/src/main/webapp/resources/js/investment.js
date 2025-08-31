/**
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
    updateInvestmentLimits(); // Ensure correct values on page load

    document.getElementById("investmentType").addEventListener("change", updateInvestmentLimits);
});

function updateInvestmentLimits() {
    const investmentType = document.getElementById("investmentType").value;
    const initialAmount = document.getElementById("initialAmount");
    const monthlyAmount = document.getElementById("monthlyAmount");

    switch (investmentType) {
        case "basicSavings":
            initialAmount.min = 0;
            monthlyAmount.min = 50;
            break;
        case "savingsPlus":
            initialAmount.min = 300;
            monthlyAmount.min = 50;
            break;
        case "managedStockInvestments":
            initialAmount.min = 1000;
            monthlyAmount.min = 150;
            break;
    }
}