//package com.shop.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//
//@Controller
//@RequestMapping("/download")
//public class FileController {
//    ResourceLoader resourceLoader;
//
//    @Autowired
//    public FileController (ResourceLoader resourceLoader){
//        this.resourceLoader = resourceLoader;
//    }
//
//    @RequestMapping("/{imgName}")
//    public ResponseEntity<Resource> resourceFileDownload(@PathVariable String imgName){
//        try{
//            Resource resource = resourceLoader.getResource()
//            File file = resource.getFile();
//        }
//
//    }
//
//}
