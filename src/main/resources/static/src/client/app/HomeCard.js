import React from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import Toggle from 'material-ui/Toggle';
import {Link} from 'react-router'

class HomeCard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        expanded: false,
        };
    }

    handleExpandChange = (expanded) => {
        this.setState({expanded: expanded});
    };

    handleToggle = (event, toggle) => {
        this.setState({expanded: toggle});
    };

    handleExpand = () => {
        this.setState({expanded: true});
    };

    handleReduce = () => {
        this.setState({expanded: false});
    };

    render() {
        return (
            <Card expanded={this.state.expanded} onExpandChange={this.handleExpandChange}>
                <CardHeader
                    title="Az egyetlen  német kupa tippjáték oldal"
                    subtitle="Mérd össze tudásodat a barátaid ellen, és lépj a toplista élére!"
                    actAsExpander={true}
                    showExpandableButton={true}
                />
                <CardText>
                    <Toggle
                        toggled={this.state.expanded}
                        onToggle={this.handleToggle}
                        labelPosition="right"
                        label="Kattintson a szabályok megtekintéséhez!"
                    />
                    A szabályok megismerése után kezdje el a játékot a Tippelés gombra kattintva!
                </CardText>
                <CardMedia expandable={true} overlay={<CardTitle title="Szabályok" />} >
                    <img src="/images/rules.png" />
                </CardMedia>
                <CardTitle title="Szabályok" expandable={true} />
                <CardText expandable={true}>
                    <p/> A játék elkezdéséhez minndössze az alábbi három szabályt érdemes észben tartani:
                         <p/> 1: Csak a rendes játékidő végeredménye számít.
                         <p/> 2: Tippelés csak a mérkőzés kezdetéig lehet leadni!
                         <p/> 3: Pontozás: <p style={{marginLeft: '35px'}}>
                             Pontos végeredmény eltalása: 3 pont.
                             <br/> Végkimenetel helyes tippelése: 1 pont.
                             <br/> Egyéb esetben: 0 pont. </p>
                </CardText>
                <CardActions>
                    <FlatButton label="Tippelés" containerElement={<Link to="/upcomingmatches" />} />
                </CardActions>
            </Card>
        );
    }
}

export default HomeCard;