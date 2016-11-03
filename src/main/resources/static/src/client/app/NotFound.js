import React from 'react';
import FlatButton from 'material-ui/FlatButton';
import {Link} from 'react-router'

const leftMarginStyle = {
	marginLeft: '100px'
};

class Ranking extends React.Component {

    render() {
        return (
			<div>
			    <h4 style={leftMarginStyle}>
                    Nincs ilyen oldal a rendszerben!
                    <br/> Kérlek az oldalon lévő linkeket használd a navigációhoz, vagy győződj meg róla, hogy helyes url-t írtál be!
                </h4>
                <FlatButton label="Vissza a kezdőlapra" containerElement={<Link to="/"/>} backgroundColor= "white" style={leftMarginStyle} />
			</div>
        )
    }
}

export default Ranking;