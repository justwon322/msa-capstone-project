
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);



import OrderInfoView from "./components/OrderInfoView"
import OrderInfoViewDetail from "./components/OrderInfoViewDetail"
import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"

import UiView from "./components/UiView"
import UiViewDetail from "./components/UiViewDetail"
import PaymentManager from "./components/listers/PaymentCards"
import PaymentDetail from "./components/listers/PaymentDetail"

import StoreManager from "./components/listers/StoreCards"
import StoreDetail from "./components/listers/StoreDetail"

import OrderConfirmView from "./components/OrderConfirmView"
import OrderConfirmViewDetail from "./components/OrderConfirmViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [

            {
                path: '/orderInfos',
                name: 'OrderInfoView',
                component: OrderInfoView
            },
            {
                path: '/orderInfos/:id',
                name: 'OrderInfoViewDetail',
                component: OrderInfoViewDetail
            },
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },

            {
                path: '/uis',
                name: 'UiView',
                component: UiView
            },
            {
                path: '/uis/:id',
                name: 'UiViewDetail',
                component: UiViewDetail
            },
            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },
            {
                path: '/payments/:id',
                name: 'PaymentDetail',
                component: PaymentDetail
            },

            {
                path: '/stores',
                name: 'StoreManager',
                component: StoreManager
            },
            {
                path: '/stores/:id',
                name: 'StoreDetail',
                component: StoreDetail
            },

            {
                path: '/orderConfirms',
                name: 'OrderConfirmView',
                component: OrderConfirmView
            },
            {
                path: '/orderConfirms/:id',
                name: 'OrderConfirmViewDetail',
                component: OrderConfirmViewDetail
            },


    ]
})
