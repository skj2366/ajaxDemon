package service.impl;

import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import dao.impl.MovieDAOImpl;
import service.MovieService;

public class MovieServiceImpl implements MovieService {

	private MovieDAO mdao = new MovieDAOImpl();
	@Override
	public List<Map<String, String>> selectMovieList() {
		return mdao.selectMovieList();
	}
	@Override
	public int insertMovie(Map<String, String> movie) {
		return mdao.insertMovie(movie);
	}
	@Override
	public int updateMovie(Map<String, String> movie) {
		return mdao.updateMovie(movie);
	}
	@Override
	public int deleteMovie(int miNum) {
		return mdao.deleteMovie(miNum);
	}
	@Override
	public Map<String, String> selectMovieByMiNum(int miNum) {
		return mdao.selectMovieByMiNum(miNum);
	}
	
//	public static void main(String[] args) {
//		MovieService ms = new MovieServiceImpl();
//		System.out.println(ms.selectListMovie());
//	}

}
