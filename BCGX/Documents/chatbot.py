import pandas as pd

# Creating the dataset directly in Python (The "Shockwave" way)
data = {
    'Company': ['Microsoft', 'Microsoft', 'Microsoft', 'Tesla', 'Tesla', 'Tesla', 'Apple', 'Apple', 'Apple'],
    'Year': [2024, 2023, 2022, 2024, 2023, 2022, 2024, 2023, 2022],
    'Total Revenue': [245122, 211915, 198270, 96773, 96773, 81462, 391035, 383285, 394328],
    'Net Income': [88136, 72361, 72738, 14974, 14974, 12556, 93736, 96995, 99803],
    'Total Assets': [512163, 411976, 364840, 119800, 106618, 82338, 352750, 352583, 352755],
    'Total Liabilities': [243686, 205753, 198298, 46200, 43009, 36440, 277327, 290437, 302083],
    'Cash Flow (Ops)': [118548, 87582, 89035, 13256, 13256, 14724, 118254, 110543, 122151]
}

df = pd.DataFrame(data)

# Sort by company and year to ensure pct_change works correctly
df = df.sort_values(by=['Company', 'Year'])
# Calculate Year-over-Year Growth
df['Revenue Growth (%)'] = df.groupby(['Company'])['Total Revenue'].pct_change() * 100
df['Net Income Growth (%)'] = df.groupby(['Company'])['Net Income'].pct_change() * 100

# Shockwave Tactical Metric: Profit Margin
df['Profit Margin (%)'] = (df['Net Income'] / df['Total Revenue']) * 100

print(df[['Company', 'Year', 'Revenue Growth (%)', 'Profit Margin (%)']])


# Assuming your 'df' from Task 1 is already loaded
def gfc_financial_bot():
    print("--- GFC AI Financial Assistant Loaded ---")
    print("Ask me about Revenue, Net Income, or Profit Margins for Apple, Microsoft, or Tesla.")
   
    while True:
        query = input("\nYou: ").lower()
        if query in ['exit', 'quit', 'stop']:
            break
           
        # Logic to determine which company the user is asking about
        company = None
        if "apple" in query: company = "Apple"
        elif "microsoft" in query: company = "Microsoft"
        elif "tesla" in query: company = "Tesla"
       
        if company:
            # Get the most recent data (2024)
            latest_data = df[(df['Company'] == company) & (df['Year'] == 2024)].iloc[0]
           
            if "revenue" in query:
                print(f"Assistant: In fiscal year 2024, {company}'s Total Revenue was ${latest_data['Total Revenue']:,}M.")
           
            elif "net income" in query or "profit" in query:
                growth = latest_data['Net Income Growth (%)']
                status = "increased" if growth > 0 else "decreased"
                print(f"Assistant: {company}'s Net Income for 2024 was ${latest_data['Net Income']:,}M. This has {status} by {abs(growth):.2f}% compared to last year.")
           
            elif "margin" in query:
                print(f"Assistant: {company} currently operates with a Profit Margin of {latest_data['Profit Margin (%)']:.2f}%.")
           
            else:
                print("Assistant: I found the data for " + company + ", but please specify if you want Revenue, Net Income, or Margins.")
        else:
            print("Assistant: Sorry, I can only provide insights for Microsoft, Tesla, and Apple based on our current 10-K data.")

# To run it:
        gfc_financial_bot()