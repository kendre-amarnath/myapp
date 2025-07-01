import streamlit as st
import pandas as pd

# Load the Excel file
@st.cache_data
def load_data():
    xl = pd.ExcelFile("Student_Data_With_Correct_College_And_Course.xlsx")
    df = pd.concat([xl.parse(sheet).assign(College=sheet) for sheet in xl.sheet_names], ignore_index=True)
    return df

df = load_data()

# App UI
st.title("🎓 Student Rank & Course Filter")

# --- Input Section ---
min_rank = st.number_input("Enter Minimum Rank", min_value=1, step=1)

max_rank = st.number_input("Enter Maximum Rank (optional)", min_value=min_rank, step=1, value=int(df['Rank'].max()))

categories = ['OC', 'SC', 'ST', 'BCA', 'BCB', 'BCC', 'BCD', 'BCE']
selected_cats = st.multiselect("Select Category (Cat)", categories, default=['OC'])

course_options = sorted(df['Course Name'].dropna().unique())
selected_course = st.selectbox("Select Course (optional)", ["All"] + course_options)

# --- Filter Logic ---
filtered_df = df[
    (df['Rank'] >= min_rank) &
    (df['Rank'] <= max_rank) &
    (df['Cat'].isin(selected_cats))
]

if selected_course != "All":
    filtered_df = filtered_df[filtered_df['Course Name'] == selected_course]

# --- Result ---
st.subheader("Filtered Results")
st.write(f"🔍 Total matching records: {len(filtered_df)}")
st.dataframe(filtered_df)

# Optional: Download button
st.download_button("📥 Download Filtered Data as CSV", filtered_df.to_csv(index=False), "filtered_results.csv")
