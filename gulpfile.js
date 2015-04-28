/* GULP modules */
var gulp 		= require('gulp'),
	less 		= require('gulp-less');

/* Misc */
var	pkg = require('./package.json');

gulp.

task('less', function () {
	try{
		gulp
			.src("src/less/main.less")
			.pipe(less())
			.pipe(gulp.dest("resources/public/css/"));
	}catch(err){
		console.log(err);
	}
}).

task('watch', function(){
	gulp.watch("src/less/**/*.less", ['less']);
}).

task('default', ['less', 'watch'])