import pandas as pd
import numpy as np
import glob

path = "C:/Users/ozgur/Downloads/fantasy-basketball-master/data/Boxscores/2014-15"
all_files = glob.glob(path + "/*.csv")

temp_list = []

for filename in all_files:
    df = pd.read_csv(filename, index_col=None, header=0)
    temp_list.append(df)

dataframe = pd.concat(temp_list, axis=0, ignore_index=True)

print(dataframe)