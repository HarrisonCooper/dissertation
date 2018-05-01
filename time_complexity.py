import matplotlib.pyplot as plt
import numpy as np

num_cells = np.array([
100,
112,
120,
122,
128,
142,
170,
186,
206,
234,
290,
322,
362,
418,
530,
594,
674,
786,
1100,
1138,
1298,
1527,
1955,
1970,
2101,
2225,
2296,
2535,
2715,
2907,
2940,
3359
])

time_taken = np.array([
0,
0,
0,
0,
0,
0,
1,
1,
1,
1,
1,
1,
1,
1,
2,
2,
2,
9,
15,
20,
28,
38,
77,
73,
94,
95,
115,
133,
177,
180,
209,
250
])

z=np.polyfit(num_cells, time_taken, 2)
p=np.poly1d(z)

xp=np.linspace(100,5000,100)
# plt.plot(num_cells, time_taken, "bx-")
# plt.plot(xp,p(xp),"r-")

# evenly sampled time at 200ms intervals
t = np.arange(0., 250., 0.2)
# red dashes, blue squares and green triangles
# plt.plot(t*t, t, 'r--')

plt.plot(num_cells,time_taken,'.',xp,p(xp),'m-')
plt.suptitle('Cells In Wound With Time')
# plt.errorbar(num_cells, time_taken, fmt='bx--')
plt.xlabel('Number of Agents')
plt.ylabel('Time Taken (minutes)')
plt.legend(title="Time Complexity")
plt.show()