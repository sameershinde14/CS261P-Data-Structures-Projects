import os
import pandas as pd
import numpy as np
import math
import json
import matplotlib.pyplot as plt
import warnings
warnings.filterwarnings("ignore")

dirs = ["BST/csv", "AVL/csv", "SplayTree/csv", "RBTree/csv"]

time_test5 = "time_inserts_test5.csv"
size_test5 = "size_inserts_test5.csv"

time_test6 = "time_removes_test6.csv"
size_test6 = "size_removes_test6.csv"

time_test7 = "time_traverse_test7.csv"
size_test7 = "size_traverse_test7.csv"

time_test8 = "time_sinserts_test8.csv"
size_test8 = "size_sinserts_test8.csv"

time_test9 = "time_sremoves_test9.csv"
size_test9 = "size_sremoves_test9.csv"


time_label = "Time"
size_label = "Size"

PLOT_LEGEND_LOC = 'upper left'
WINDOW_LENGTH = 1
WINDOW_STRIDE = 1

def load_data(file, header=True):
    csv_path = os.path.join("", file)
    if header:
        return pd.read_csv(csv_path)
    else:
        return pd.read_csv(csv_path, header=None)

def data_smoother(data, window_length=WINDOW_LENGTH, window_stride=WINDOW_STRIDE):
    index = window_length
    smooth_data = []
    while True:
        if index > len(data): break
        fr = np.int(index - window_length)
        to = np.int(index)
        w = data[fr:to]
        smooth_data.append(sum(w) * 1.0 / window_length)
        index = index + window_stride
            
    return smooth_data

def plot(dir_name, loss_file_name, loss_label, scores_file_name, scores_label, graph_name, header=False):
    dataX = load_data(dir_name + "/" + loss_file_name, header=header).values.flatten()
    dataY = load_data(dir_name + "/" + scores_file_name, header=header).values.flatten()
    #data_smoothX = data_smoother(dataX)
    #data_smoothY = data_smoother(dataY)
    #plt.plot(data_smoothY, data_smoothX)
    
    
    fileName = dir_name + "/" + loss_file_name;
    print(dir_name+" and "+ graph_name)
    print("Total Time:")
    print(np.sum(dataX,axis=0))
    
    plt.plot(dataY, dataX)
    plt.xlabel('Size')
    plt.ylabel('Time')
    plt.legend(loc=PLOT_LEGEND_LOC)
    plt.title(graph_name)
    #plt.xlabel("Episodes # (Running mean of " + str(window_length) + " with stride " + str(WINDOW_STRIDE) + ")")
    strFile = dir_name + "/Graphs/"+ graph_name + ".jpg"
    if os.path.isfile(strFile):
       os.remove(strFile)   # Opt.: os.system("rm "+strFile)
    plt.savefig(strFile)
    plt.show()

for dir_each in dirs:
    plot(dir_each, time_test5, time_label,size_test5, size_label, "INSERTS" )
    plot(dir_each, time_test6, time_label,size_test6, size_label, "REMOVES")
    plot(dir_each, time_test7, time_label,size_test7, size_label, "TRAVERSE")
    plot(dir_each, time_test8, time_label,size_test8, size_label, "SKEWED INSERTS")
    plot(dir_each, time_test9, time_label,size_test9, size_label, "SKEWED REMOVES")
    #plot(dir_each, scores_file_name, scores_label)