import os

if __name__=="__main__":
    user="ldy"
    password="abcd1234"
    IMPORT_SQL = "mysql -u {} -p{} < quarkcommunity.sql".format(user,password);
    ADJUST_SQL = "mysql -u {} -p{} < adjustsql.sql".format(user,password)
    os.system(IMPORT_SQL)
    os.system(ADJUST_SQL)