# about dto,关于rest 返回的优化
* return dto 里面的 ,status 码需要重新设计下，error code
也是，这里应用层的status_code 应该用应用层自己设计的code,
而不是http code
* extra 里面加个通用信息 
    * now 时间戳
    * next page 
    * has_more 