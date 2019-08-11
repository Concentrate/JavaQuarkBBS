# about dto,关于rest 返回的优化
* return dto 里面的 ,status 码需要重新设计下，error code
也是，这里应用层的status_code 应该用应用层自己设计的code,
而不是http code
* extra 里面加个通用信息 
    * now 时间戳
    * next page 
    * has_more 
    
* innoe db 全文索引加上，优化搜索效果
* cache 里面 redis 和ehcache方案，组合，现在
rest service 里面用的是redis(待定)