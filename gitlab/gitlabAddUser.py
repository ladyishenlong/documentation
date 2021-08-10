import json
import pandas
import openpyxl
import requests

# 用于批量导入gitlab用户

# 需要指定镜像源否则安装库和升级都有问题
# pip install xlrd -i http://pypi.douban.com/simple --trusted-host pypi.douban.com
# pip3 install -U pip -i http://pypi.douban.com/simple --trusted-host pypi.douban.com


def excel():
    df = pandas.read_excel('/user.xlsx')
    user_infos = df.values
    for num in range(100, 103):
        print("当前:", num)
        user_info = user_infos[num]
        password = user_info[0]
        name = user_info[1]
        username = user_info[2]
        email = user_info[3]
        print(password, name, username, email)
        url = "https:// {gitlab的域名} /api/v4/users"

        body = {
            "email": email,
            "username": username,
            "password": password,
            "name": name,
            "skip_confirmation": "true"
        }

        header = {'content-type': "application/json",
                  'cache-control': 'no-cache',
                  'PRIVATE-TOKEN': '{gitlab的访问令牌}'}

        data = json.dumps(body)
        result = requests.post(url, headers=header, data=data)
        print(result)



if __name__ == '__main__':
    print('开始读取数据！！！')
    excel()
