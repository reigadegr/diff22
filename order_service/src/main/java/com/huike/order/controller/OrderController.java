/* Copyright 2024 reigadegr@gitbub.com
 *
 *  Licensed under the Apache License, Version 2.0 (the
 * License);
 *  you may not use this file except in compliance with the License.
 *  You may obtain
 * a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless
 * required by applicable law or agreed to in writing, software
 *  distributed under the License is
 * distributed on an AS IS BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *  See the License for the specific language governing permissions and
 *
 * limitations under the License. */
package com.huike.order.controller;

import com.huike.order.command.OrderCommand;
import com.huike.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    // 注入restTemplate对象，实现微服务间调用
    @Autowired
    private RestTemplate restTemplate;

    //    @Autowired
    //    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://service-product/product/" + id, Product.class);
    }

    @RequestMapping(value = "/buy2/{id}", method = RequestMethod.GET)
    public Product findById2(@PathVariable Long id) {
        return new OrderCommand(restTemplate, id).execute();
    }

    @RequestMapping(value = "/cg/{cg}", method = RequestMethod.GET)
    public Product findByCg(@PathVariable String cg) {
        return restTemplate.getForObject("http://service-product/product/cg/" + cg, Product.class);
    }
}
