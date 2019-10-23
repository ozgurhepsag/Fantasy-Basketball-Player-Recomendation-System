import pandas as pd
import numpy as np
import math
import pickle
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score

# path = "C:/Users/ozgur/Downloads/fantasy-basketball-master/data/Boxscores/2014-15"
# all_files = glob.glob(path + "/*.csv")

# temp_list = []

# for filename in all_files:
#     df = pd.read_csv(filename, index_col=None, header=0)
#     temp_list.append(df)

# dataframe = pd.concat(temp_list, axis=0, ignore_index=True)

# print(dataframe)

# missing_data = dataframe.isnull()

# for column in missing_data.columns.values.tolist():
#     print(column)
#     print (missing_data[column].value_counts())
#     print("")

# for index, row in dataframe.iterrows():
#     if row['3PA'] is 0 and row.isnull()['3P_perc']:
#         dataframe.loc[index, ['3P_perc']] = 0

# for index, row in dataframe.iterrows():
#     if row['FGA'] is 0 and row.isnull()['FG_perc']:
#         dataframe.loc[index, ['FG_perc']] = 0

# for index, row in dataframe.iterrows():
#     if row['FTA'] is 0 and row.isnull()['FT_perc']:
#         dataframe.loc[index, ['FT_perc']] = 0

merged_path = "C:/Users/ozgur/Desktop/dev/Fantasy-Basketball-Player-Recomendation-System/ML/main_merged2.csv"
merged_df = pd.read_csv(merged_path, index_col=None, header=0)
merged_df.Date = pd.to_datetime(merged_df.Date)
print(merged_df.dtypes)

MONTH = 30

temp_df = merged_df.drop(['Pos', 'Starter', 'Team', 'Home', 'W',
       'FG', 'FGA', 'FG_perc', 'USG_perc', '3PA', '3P_perc', 'FTA',
       'FT_perc', 'TRB', 'AST', 'STL', 'BLK', 'PF', 'PTS',
       'TOV', 'DRtg', 'AST_perc', 'DRB_perc', 'ORB_perc', 'BLK_perc',
       'TOV_perc', 'STL_perc', 'eFG_perc', 'PG', 'SG', 'F', 'C',
       'PTS_AVG Last Month', 'PTS_AVG Last Week', 'AST Last Week', 'PTS_AVG',
       'AST Last Month', 'MP_AVG', 'FG_AVG', 'FGA_AVG', 'FG_perc_AVG',
       'FG_AVG Last Week', 'FGA_AVG Last Week', 'FG_perc_AVG Last Week',
       'AST_AVG', 'FG_AVG Last Month', 'FGA_AVG Last Month',
       'FG_perc_AVG Last Month', 'Opp', 'BLK_AVG', 'BLK Last Week',
       'BLK Last Month', 'TRB_AVG', 'STL_AVG'], axis=1)
merged_df['FT Last Month'] = np.nan

for index_i, row_i in temp_df.iterrows():
    count = 0
    total = 0

    for index_j, row_j in temp_df.iterrows():
        if index_j >= index_i:
            break
        elif (row_i['Name'] == row_j['Name']) and (row_i['Date'] - row_j['Date']).days <= MONTH:  
            count += 1
            total += row_j['FT']
     
    if total is 0:
        merged_df.loc[index_i, ['FT Last Month']] = 0
    else:
        merged_df.loc[index_i, ['FT Last Month']] = total / count
    
    print(index_i, total, count, merged_df.loc[index_i, ['FT Last Month']])
    
merged_df.to_csv("merged13.csv", encoding='utf-8', index=False)


# merged_path = "C:/Users/ozgur/Desktop/dev/Fantasy-Basketball-Player-Recomendation-System/ML/main_merged2.csv"
# merged_df = pd.read_csv(merged_path, index_col=None, header=0)
# merged_df.drop(['Pos', 'Date', 'STL_AVG', 'BLK Last Week',
#        'USG_perc', 'DRtg', 'ORtg', 'AST_perc', 'DRB_perc', 'ORB_perc', 'Starter',
#        'BLK_perc', 'TOV_perc', 'STL_perc', 'eFG_perc', 'MP', 'FG', 'FGA',
#                'FG_perc', '3P', '3PA', '3P_perc', 'FT', 'FTA', 'BLK Last Month', 'TRB_AVG',
#        'FT_perc', 'TRB', 'STL', 'BLK', 'TOV', 'PF', 'W', 'BLK_AVG', 'FG_AVG', 'FGA_AVG', 'FG_perc_AVG', 'FG_AVG Last Week',
#        'FGA_AVG Last Week', 'FG_perc_AVG Last Week', 'FG_AVG Last Month',
#        'FGA_AVG Last Month', 'FG_perc_AVG Last Month', 'AST Last Month', 'AST Last Week', 'AST_AVG', 'AST'], axis=1, inplace=True)

# train = merged_df.loc[1609:10000]
# test = merged_df.loc[10000:10500]
# X_train = train.drop(['PTS', 'Name', 'Team', 'Opp'], 1)
# y_train = train[['PTS']]
# X_test = test.drop(['PTS', 'Name', 'Team', 'Opp'], 1)
# y_test = test[['PTS']]

# lm = LinearRegression()
# lm.fit(X_train, y_train)
# y_pred = lm.predict(X_test)

# row = pd.DataFrame({'Home':1 ,'PG':1,  'SG':0,  'F':0,  'C':0 ,  'PTS_AVG Last Month':7.133333 ,   'PTS_AVG Last Week':7.25 ,  'PTS_AVG':8.962963 ,  'MP_AVG':24.776296}, ['Home', 'PG', 'SG', 'F', 'C', 'PTS_AVG Last Month', 'PTS_AVG Last Week', 'PTS_AVG', 'MP_AVG'])
# rowhat = lm.predict(row)
# print(y_pred, rowhat)

# filename = 'lm.pkl'
# pickle.dump(lm, open(filename, 'wb'))