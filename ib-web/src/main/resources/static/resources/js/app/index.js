(function() {
  "use strict";
  var models = ['jquery',
    'vue',
    'pin',
    'bootstrap',
    'css!style/pin',
    'css!style/bootstrap/bootstrap.min',
    'css!style/public'
  ];
  define(models, function($, Vue) {

    Vue.component('todo-item', {
      template: '<li>这是个待办项</li>'
    });

    Vue.component('todo-item2', {
      // todo-item 组件现在接受一个
      // "prop"，类似于一个自定义特性。
      // 这个 prop 名为 todo。
      props: ['todo'],
      template: '<li>{{ todo.text }}</li>'
    });

    function init() {

      $(".my-pin").pin({
        containerSelector: ".my-pin-container"
      });
      $(".my-pin2").pin({
        //containerSelector: ".my-pin-container"
      });

      window.app1 = new Vue({
        el: '#app1',
        data: {
          message: 'Hello Vue!'
        },
        computed: {
          reversedMessage: function() {
            // `this` 指向 vm 实例
            return this.message.split('').reverse().join('')
          }
        },
        methods: {
          reversedMessageM: function() {
            return this.message.split('').reverse().join('')
          }
        }

      });
      app1.$watch('message', function(newValue, oldValue) {
        console.log(newValue, oldValue);
      });

      window.app2 = new Vue({
        el: '#app2',
        data: {
          message: 'Hello Vue!'
        }
      });

      window.app3 = new Vue({
        el: '#app3',
        data: {
          groceryList: [{
            id: 0,
            text: '蔬菜'
          }, {
            id: 1,
            text: '奶酪'
          }, {
            id: 2,
            text: '随便其它什么人吃的东西'
          }]
        }
      });
    }


    return {
      render: function() {
        init();
      }
    }
  });
})();