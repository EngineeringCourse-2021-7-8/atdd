# language: zh-CN
@api-login
功能: 订单

  场景: 订单列表
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status        |
      | SN001 | 电脑          | 19999 | 张三            | 13085901735     | 上海市长宁区           | toBeDelivered |
    并且存在订单"SN001"的订单项:
      | itemName | price | quantity |
      | MacBook  | 19999 | 1        |
      | TouchPad | 0     | 1        |
    当API查询订单时
    那么返回如下订单
    """
      [{
        "code": "SN001",
        "productName": "电脑",
        "total": 19999,
        "recipientName": "张三",
        "recipientMobile": "13085901735",
        "recipientAddress": "上海市长宁区",
        "status": "toBeDelivered",
        "lines": [{
          "itemName": "MacBook",
          "price": 19999,
          "quantity": 1
        }, {
          "itemName": "TouchPad",
          "price": 0,
          "quantity": 1
        }]
      }]
    """

  场景: 订单详情
    假如存在如下订单:
      | code  | productName | total | recipientName | recipientMobile | recipientAddress | status     | deliverNo     |
      | SN001 | 电脑          | 19999 | 张三            | 13085901735     | 上海市长宁区           | delivering | 4313751158896 |
    并且存在快递单"4313751158896"的物流信息如下
    """
    {
        "status": 0,
        "msg": "ok",
        "result": {
            "number": "4313751158896",
            "type": "yunda",
            "typename": "韵达快运",
            "logo": "https://api.jisuapi.com/express/static/images/logo/80/yunda.png",
            "list": [
                {
                    "time": "2021-04-16 23:51:55",
                    "status": "【潍坊市】已离开 山东潍坊分拨中心；发往 成都东地区包"
                },
                {
                    "time": "2021-04-16 23:45:47",
                    "status": "【潍坊市】已到达 山东潍坊分拨中心"
                },
                {
                    "time": "2021-04-16 16:47:35",
                    "status": "【潍坊市】山东青州市公司-赵良涛(13606367012) 已揽收"
                }
            ],
            "deliverystatus": 1,
            "issign": 0
        }
    }
    """
    当API查询订单"SN001"详情时
    那么返回如下订单
    """
      {
        "code": "SN001",
        "productName": "电脑",
        "total": 19999,
        "recipientName": "张三",
        "recipientMobile": "13085901735",
        "recipientAddress": "上海市长宁区",
        "status": "delivering",
        "lines": [],
        "logistics": {
            "deliverNo": "4313751158896",
            "companyCode": "yunda",
            "companyName": "韵达快运",
            "companyLogo": "https://api.jisuapi.com/express/static/images/logo/80/yunda.png",
            "details": [
                {
                    "time": "2021-04-16 23:51:55",
                    "status": "【潍坊市】已离开 山东潍坊分拨中心；发往 成都东地区包"
                },
                {
                    "time": "2021-04-16 23:45:47",
                    "status": "【潍坊市】已到达 山东潍坊分拨中心"
                },
                {
                    "time": "2021-04-16 16:47:35",
                    "status": "【潍坊市】山东青州市公司-赵良涛(13606367012) 已揽收"
                }
            ],
            "deliveryStatus": "在途中",
            "isSigned": "未签收"
        }
    }
    """
