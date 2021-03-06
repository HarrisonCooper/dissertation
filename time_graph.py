import matplotlib.pyplot as plt

# bins=["00-05","05-10","10-15","15-20","20-25"]

# time_to_heal = [
# 30,
# 34.8,
# 39.6,
# 44.4,
# 49.4,
# 48
# ]

# bins = [
# 0,
# 2.74,
# 6.98,
# 12.88,
# 17.28,
# 23.54
# ]

# yerror = [
# 0,
# 1.073312629,
# 1.314534138,
# 1.314534138,
# 1.867618805,
# 1.697056275
# ]

# xerror = [
# 0,
# 0.060663004,
# 0.357099426,
# 0.450244378,
# 0.666573327,
# 0.885708756
# ]

time_to_heal = [
30,
30,
30,
30,
30,
36,
30,
36,
36,
36,
36,
36,
42,
42,
42,
42,
42,
42,
48,
48,
42,
48,
54,
48,
54,
48,
48,
54,
42,
48
]

senescence_perc = [
0,
0,
0,
0,
0,
2.6,
2.6,
2.7,
2.9,
2.9,
5.5,
6.8,
7.4,
7.5,
7.7,
11.7,
12.1,
12.8,
13.2,
14.6,
15.3,
16.7,
16.8,
17.8,
19.8,
20.1,
23.3,
23.4,
25,
25.9
]

# time_to_heal = [
# 21,
# 21,
# 27,
# 29,
# 31,
# 36,
# 37,
# 34,
# 38,
# 46,
# 39,
# 44
# ]

# senescence_perc = [
# 0,
# 0,
# 2.7,
# 4,
# 5.7,
# 7.5,
# 12.2,
# 12.6,
# 16.1,
# 18.9,
# 20,
# 24
# ]

#1 Hour Time Steps
# time_to_heal = [
# 21,
# 28,
# 33.5,
# 35.5,
# 42,
# 41.5
# ]

# senescence_perc = [
# 0,
# 3.35,
# 6.6,
# 12.4,
# 17.5,
# 22
# ]

# yerror = [
# 0,
# 0.707106781,
# 0.707106781,
# 1.060660172,
# 2.828427125,
# 1.767766953
# ]

# xerror = [
# 0,
# 0.459619408,
# 0.254558441,
# 0.141421356,
# 0.989949494,
# 1.414213562
# ]

plt.suptitle('Time Taken For Wound To Heal At Varying Senescence')
# plt.errorbar(senescence_perc, time_to_heal, yerr=yerror, xerr=xerror, ecolor="black", fmt='bx--', capsize=5)
# plt.errorbar(bins, time_to_heal, yerr=yerror, xerr=xerror, ecolor="black", fmt='bx--', capsize=5)
plt.errorbar(senescence_perc, time_to_heal, fmt='bx')
plt.xlabel('Confluence Senescnece (%)')
plt.ylabel('Time to Heal (Hrs)')
plt.show()