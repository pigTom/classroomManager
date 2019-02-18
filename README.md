## classroomManager 教室管理系统
   教室管理系统主要分为管理员端和用户端，功能和一般系统大同小异。
  
   主要讲讲我的写这个项目的一些经验吧，前端和后端一起写确实有些吃力，幸亏这次我用了别人
写好的前端页面，不过页面还是很丑，这只是学过一点jquery,没有学过什么前端框架，所以界面
也可以想象。在写项目之前最好把项目的需求书完成，项目的大体结构心中要有数，否则后期会非
常难受，在项目命名方面也是，这些东西只能自行积累了。还有就是性能了，前端访问数据后，本
应该再次访问时后台不应该再从数据库中查询数据了，因为如果数据很多的话，每次查询将会变得
很慢，也会让占据很多后台的内存。但是在数据有更新后，特别是像改一些信息时，数据发生了一
些变化，我的确想在后台把数据进行更新，数据库也进行更新，从而数据像重新查询一样是崭新的
。但是我用的是自己的PageInfo类，将其放入session后，要将其取出，然后将其更新会变得很麻
烦，所以我统一没有进行更新。我想如果将一页看成一个单独的对象，在数据进行更新时，从页中
抽取数据会显得高效一些。

    我主要想说说我的一些不足的地方：
    
        1、 没有安全机制，具体说我不知道怎么去做到让网站安全，我只是知道通过session能
            唯一地确定一个用户，具体说是一个浏览器一定时间的访问，因为session有生存周
            期。我只是对管理员和用户有登录时，把登录者的一些信息放入session中，在某些
            特定的时候，取出session，进行身份检测（应该是在某些存在敏感事件的时候），
            如果session非法，就会发生地址重定向，将用户返回登录界面。
            
        2、 对于数据的存取没有采用统一的接口，造成数据的存取往往需要花费许多时间。
        
        3、 对数据库的访问增删改查，如何设定一些基本方法，实现这些功能，然后后面的类去
            继承这些特性，比如一些基本的接口，这说的很抽象，连自己都不清楚，但是感觉应
            该要往这边想。
            
        4、 接下来想说一下前端的问题，我这个项目里刚开始是没有用ajax进行数据交换，但是
            越到后面我越发现，把数据通过ajax能更友好，或者说更高效，对页面有更好的控制
            但是如何通过ajax传却是一个问题，比如将多个对象传到前端，怎么想用foreach那
            样简单地用ajax这也是一个问题。
            
        5、 其实还有很多问题，但是我一下子想不起来了，以后再说吧。
    
这觉得把自己做的项目管理起来，真的很好，而且还是免费的。后期可以更好的根据自己的项目发觉自己的问题。同时也能保存自己的一些好思想， 以后说不定能用得着。
