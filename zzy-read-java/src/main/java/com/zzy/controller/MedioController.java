package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.common.ZZYUtils;
import com.zzy.entity.Essay;
import com.zzy.entity.Medio;
import com.zzy.entity.Params;
import com.zzy.mapper.EssayMapper;
import com.zzy.mapper.MedioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class MedioController {

    @Autowired
    private MedioMapper medioMapper;
    @Autowired
    private EssayMapper essayMapper;
    @PostMapping("/admin/upload")
    public Successful<Medio> uploadMedio(Integer eid , MultipartFile file, HttpServletRequest request) throws IOException {
        Successful<Medio> successful = new Successful<>();
        QueryWrapper<Medio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        Medio medio1 = medioMapper.selectOne(queryWrapper);
        if (medio1 != null){
            successful.setState(State.ERROR);
            successful.setMessage("添加视频失败 已有教学视频");
        }else {
            //获取文件名
            String originalFilename = file.getOriginalFilename();
            //获取后缀名
            String[] split = originalFilename.split("\\.");
            String pre = split[split.length - 1];
            String fileName = ZZYUtils.getFileName(pre);
            File medioFile = ZZYUtils.createMedioFile(fileName);
            //存入
            file.transferTo(medioFile);
            //创建文件网路路径 映射
            int serverPort = request.getServerPort();
            String IP = InetAddress.getLocalHost().getHostAddress();
            String medioRemotePath = "http://" + IP + ":" + serverPort + "/static" + "/" + fileName;
            System.out.println(medioRemotePath);

            //插入数据库
            Medio medio = new Medio(eid, medioRemotePath, new Date());
            int insert = medioMapper.insert(medio);
            if (insert != -1){
                essayMapper.updateEssaymHas(eid);
            }
            successful.setState(State.SUCCEED);
            successful.setMessage("添加视频成功");
            successful.setData(medio);
        }
        return successful;
    }
    @DeleteMapping("/admin/delmedio/{eid}")
    public Successful<String> delMedio(@PathVariable Integer eid){
        Successful<String> successful = new Successful<>();
        QueryWrapper<Medio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        int delete = medioMapper.delete(queryWrapper);
        if (delete != -1){
            essayMapper.updateEssayIdNot(eid);
        }
        successful.setState(State.SUCCEED);
        successful.setMessage("删除视频成功");
        return successful;
    }
    @GetMapping("/admin/getAllMedio")
    public Successful<List<Params>> getAllMedio(){
        Successful<List<Params>> successful = new Successful<>();
        List<Medio> medios = medioMapper.selectList(null);
        List<Params> list = new ArrayList<>();
        if (medios != null){
            for (Medio medio : medios){
                QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("e_id",medio.getEId());
                Essay essay = essayMapper.selectOne(queryWrapper);
                Params params = new Params();
                params.setP1(essay.getTitle());
                params.setP2(essay.getAuthor());
                params.setP3(essay.getIntro());
                params.setP4(essay.getContent());
                params.setP5(medio.getEId());
                list.add(params);
            }
        }
        successful.setState(State.SUCCEED);
        successful.setMessage("查找所有视频成功");
        successful.setData(list);
        return successful;
    }
    @GetMapping("/getMedio/{eid}")
    public Successful<String> getMedioPath(@PathVariable Integer eid){
        Successful<String> successful = new Successful<>();
        QueryWrapper<Medio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        Medio medio = medioMapper.selectOne(queryWrapper);
        successful.setState(State.SUCCEED);
        successful.setMessage("请求路径成功");
        successful.setData(medio.getMedioPath());
        return successful;
    }
}
